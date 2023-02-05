package com.aledev.avaliacaotecnica.v1.service.impl;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.exception.*;
import com.aledev.avaliacaotecnica.v1.model.ValidationCpfDto;
import com.aledev.avaliacaotecnica.v1.repository.VoteRepository;
import com.aledev.avaliacaotecnica.v1.service.SessionService;
import com.aledev.avaliacaotecnica.v1.service.VoteService;
import com.aledev.avaliacaotecnica.v1.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    private static final String CPF_NOT_ABLE_TO_VOTE = "UNABLE_TO_VOTE";
    private final VoteRepository voteRepository;
    private final RestTemplate restTemplate;
    private final SessionService sessionService;
    private final VotingService votingService;
    @Value("${app.integracao.cpf.url}")
    private String urlCpfValidator;

    @Autowired
    public VoteServiceImpl(RestTemplate restTemplate,
                           VoteRepository voteRepository,
                           SessionService sessionService,
                           VotingService votingService) {

        this.restTemplate = restTemplate;
        this.voteRepository = voteRepository;
        this.sessionService = sessionService;
        this.votingService = votingService;
    }

    @Override
    public Voto findById(Long id) {
        return voteRepository.findById(id)
                .orElseThrow(VoteNotFoundException::new);
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
        return voteRepository.save(voto);
    }

    @Override
    public void verifyVoto(final Session session, final Voto voto) {

        LocalDateTime dataLimite = session.getDataInicio().plusMinutes(session.getMinutosValidade());
        if (LocalDateTime.now().isAfter(dataLimite)) {
            throw new SessionTimeOutException();
        }
        cpfAbleToVote(voto);
        voteAlreadyExists(voto);
    }
    @Override
    public void voteAlreadyExists(final Voto voto) {
        voteRepository.findByCpfAndStaffId(voto.getCpf(), voto.getStaff().getId())
                .orElseThrow(VoteAlreadyExistsException::new);
    }

    @Override
    public void cpfAbleToVote(final Voto voto) {
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
        return voteRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        var votoById =
                voteRepository.findById(id)
                        .orElseThrow(VoteNotFoundException::new);
        voteRepository.delete(votoById);
    }

    @Override
    public void deleteByStaffId(Long id) {
        var votos = voteRepository.findByStaffId(id);
        votos.ifPresent(voto -> voto.forEach(voteRepository::delete));
    }

    @Override
    public List<Voto> findVotesByStaffId(Long id) {
        return voteRepository.findByStaffId(id)
                .orElseThrow(VoteNotFoundException::new);
    }
}
