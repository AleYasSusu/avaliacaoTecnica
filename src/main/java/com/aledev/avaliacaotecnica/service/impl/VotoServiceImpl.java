package com.aledev.avaliacaotecnica.service.impl;


import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.exception.*;
import com.aledev.avaliacaotecnica.model.ValidationCpfDto;
import com.aledev.avaliacaotecnica.repository.VoteRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

	private static final String CPF_NOT_ABLE_TO_VOTE = "UNABLE_TO_VOTE";

	@Value("${app.integracao.cpf.url}")
	private String urlCpfValidator;

	private final VoteRepository votoRepository;

	private final RestTemplate restTemplate;

	private final SessionService sessionService;

	@Override
	public Voto findById(Long id) {
		Optional<Voto> findById = votoRepository.findById(id);
		if(!findById.isPresent()){
			throw new VoteNotFoundException();
		}
		return findById.get();
	}

	@Override
	public Voto createVote(Long staffId, Long sessionId, Voto voto) {
		var session = sessionService.findByIdSessionAndStaffId(sessionId, staffId);
		if (!staffId.equals(session.getStaff().getId())) {
			throw new InvalidSessionException();
		}
		voto.setStaff(session.getStaff());
		return verifyAndSave(session, voto);
	}

	private Voto verifyAndSave(final Session session, final Voto voto) {
		verifyVoto(session, voto);
		return votoRepository.save(voto);
	}

	protected void verifyVoto(final Session session, final Voto voto) {

		LocalDateTime dataLimite = session.getDataInicio().plusMinutes(session.getMinutosValidade());
		if (LocalDateTime.now().isAfter(dataLimite)) {

			throw new SessionTimeOutException();
		}
		cpfAbleToVote(voto);
		voteAlreadyExists(voto);
	}

	private void voteAlreadyExists(final Voto voto) {
		var voteByCpfAndStaff = votoRepository.findByCpfAndStaffId(voto.getCpf(), voto.getStaff().getId());

		if (voteByCpfAndStaff.isPresent()) {
			throw new VoteAlreadyExistsException();
		}
	}

	private void cpfAbleToVote(final Voto voto) {
		ResponseEntity<ValidationCpfDto> cpfValidation = getCpfValidation(voto);
		if (HttpStatus.OK.equals(cpfValidation.getStatusCode())) {
			if (CPF_NOT_ABLE_TO_VOTE.equalsIgnoreCase(cpfValidation.getBody().getStatus())) {
				throw new CpfUnableException();
			}
		} else {
			throw new InvalidCpfException();
		}
	}

	private ResponseEntity<ValidationCpfDto> getCpfValidation(final Voto voto) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		return restTemplate.exchange(urlCpfValidator.concat("/").concat(voto.getCpf()), HttpMethod.GET, entity,
				ValidationCpfDto.class);
	}

	@Override
	public List<Voto> findAll() {
		return votoRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		Optional<Voto> votoById = votoRepository.findById(id);
		if (!votoById.isPresent()) {
			throw new VoteNotFoundException();
		}
		votoRepository.delete(votoById.get());
	}

	@Override
	public void deleteByStaffId(Long id) {
		Optional<List<Voto>> votos = votoRepository.findByStaffId(id);
		votos.ifPresent(voto -> voto.forEach(votoRepository::delete));
	}

	@Override
	public List<Voto> findVotesByStaffId(Long id) {
		var findByStaffId = votoRepository.findByStaffId(id);

		if (!findByStaffId.isPresent()) {
			throw new VoteNotFoundException();
		}

		return findByStaffId.get();
	}
}
