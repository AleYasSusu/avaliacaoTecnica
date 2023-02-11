package com.aledev.avaliacaotecnica.v1.service.impl;


import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.entity.Staff;
import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.exception.CpfUnableException;
import com.aledev.avaliacaotecnica.v1.exception.SessionTimeOutException;
import com.aledev.avaliacaotecnica.v1.exception.VoteAlreadyExistsException;
import com.aledev.avaliacaotecnica.v1.model.ValidationCpfDto;
import com.aledev.avaliacaotecnica.v1.model.VotingDto;
import com.aledev.avaliacaotecnica.v1.repository.VoteRepository;
import com.aledev.avaliacaotecnica.v1.service.SessionService;
import com.aledev.avaliacaotecnica.v1.service.VotingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class VoteServiceTest {
    @InjectMocks
    private VoteServiceImpl voteService;
    @Mock
    private VoteRepository votoRepository;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private VotingService votingService;
    @Mock
    private SessionService sessionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        voteService = new VoteServiceImpl(restTemplate, votoRepository, sessionService, votingService);
    }

    @Test(expected = SessionTimeOutException.class)
    public void verifyVotoTest() {
        Session sessao = new Session();
        sessao.setDataInicio(LocalDateTime.now());
        sessao.setMinutosValidade(-1L);

        Voto voto = new Voto();
        Staff staff = new Staff();
        staff.setId(1L);
        voto.setStaff(staff);

        when(votingService.buildVotingStaff(anyLong())).thenReturn(VotingDto.builder().build());

        voteService.verifyVoto(sessao, voto);
    }

    @Test
    public void cpfAbleToVoteTest() {
        Voto voto = new Voto();
        voto.setCpf("1234");

        ValidationCpfDto cpf = new ValidationCpfDto();
        cpf.setStatus("TESTE");

        ResponseEntity<ValidationCpfDto> response = new ResponseEntity<>(cpf, HttpStatus.NOT_FOUND);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(ValidationCpfDto.class)))
                .thenReturn(response);

        voteService.cpfAbleToVote(voto);
    }

    @Test(expected = CpfUnableException.class)
    public void cpfAbleToVote2Test() {
        Voto voto = new Voto();
        voto.setCpf("1234");

        ValidationCpfDto cpf = new ValidationCpfDto();
        cpf.setStatus("UNABLE_TO_VOTE");

        ResponseEntity<ValidationCpfDto> response = new ResponseEntity<>(cpf, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(ValidationCpfDto.class)))
                .thenReturn(response);

        voteService.cpfAbleToVote(voto);
    }

    @Test
    public void cpfAbleToVote3Test() {
        Voto voto = new Voto();
        voto.setCpf("1234");

        ValidationCpfDto cpf = new ValidationCpfDto();
        cpf.setStatus("ABLE_TO_VOTE");

        ResponseEntity<ValidationCpfDto> response = new ResponseEntity<>(cpf, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(ValidationCpfDto.class)))
                .thenReturn(response);

        voteService.cpfAbleToVote(voto);
    }

    @Test
    public void votoAlreadyExistsTest() {
        Voto voto = new Voto();
        voto.setCpf("1234");
        Staff staff = new Staff();
        staff.setId(1L);
        voto.setStaff(staff);
        when(votoRepository.findByCpfAndStaffId(anyString(), anyLong())).thenReturn(Optional.of(new Voto()));
        voteService.voteAlreadyExists(voto);
    }

    @Test(expected = VoteAlreadyExistsException.class)
    public void votoAlreadyExistssTest() {
        Voto voto = new Voto();
        voto.setCpf("1234");
        voto.setId(1L);
        Staff staff = new Staff();
        staff.setId(1L);
        voto.setStaff(staff);

        when(votoRepository.findByCpfAndStaffId(anyString(), anyLong())).thenReturn(Optional.empty());
        voteService.voteAlreadyExists(voto);
    }

}
