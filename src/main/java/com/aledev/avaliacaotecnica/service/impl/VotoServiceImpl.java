package com.aledev.avaliacaotecnica.service.impl;


import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.exception.*;
import com.aledev.avaliacaotecnica.model.ValidationCpfDto;
import com.aledev.avaliacaotecnica.repository.VoteRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {

    private static final String CPF_NOT_ABLE_TO_VOTE = "UNABLE_TO_VOTE";

    @Value("${app.integracao.cpf.url}")
    private String urlCpfValidator;
    @Autowired
    private VoteRepository votoRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SessionService sessionService;

    @Override
    public Voto findById(Long id) {
        var findById = votoRepository.findById(id)
                .orElseThrow(() -> new VoteNotFoundException());
        return findById;
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
        votoRepository.findByCpfAndStaffId(voto.getCpf(), voto.getStaff().getId())
                .orElseThrow(VoteAlreadyExistsException::new);
    }

    private void cpfAbleToVote(final Voto voto) {
        var cpfValidation = getCpfValidation(voto);
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
        var votoById =
                votoRepository.findById(id)
                        .orElseThrow(VoteNotFoundException::new);
        votoRepository.delete(votoById);
    }

    @Override
    public void deleteByStaffId(Long id) {
        var votos = votoRepository.findByStaffId(id);
        votos.ifPresent(voto -> voto.forEach(votoRepository::delete));
    }

    @Override
    public List<Voto> findVotesByStaffId(Long id) {
        return votoRepository.findByStaffId(id)
                        .orElseThrow(VoteNotFoundException::new);
    }
}
