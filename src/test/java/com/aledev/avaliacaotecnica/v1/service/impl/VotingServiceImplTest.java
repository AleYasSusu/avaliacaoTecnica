package com.aledev.avaliacaotecnica.v1.service.impl;


import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.exception.VoteNotFoundException;
import com.aledev.avaliacaotecnica.v1.model.VotingDto;
import com.aledev.avaliacaotecnica.v1.repository.SessionRepository;
import com.aledev.avaliacaotecnica.v1.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.aledev.avaliacaotecnica.builder.VotingDtoBuilder.buildVotingDtoDefault;
import static com.aledev.avaliacaotecnica.builder.VotoBuilder.buildVotoDefault;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotingServiceImplTest {
    @InjectMocks
    private VotingServiceImpl votingService;
    @Mock
    private VoteRepository voteRepository;

    @Mock
    private SessionRepository sessaoRepository;

    @Test
    public void shouldSaveVOte() {
        //Data
        Voto voto = buildVotoDefault().build();

        when(voteRepository.save(voto))
                .thenReturn(voto);

        //Action
        Voto result = votingService.save(voto);

        //Result
        assertThat(result).isEqualTo(voto);
        verify(voteRepository, times(1)).save(voto);
    }

    @Test
    public void shouldReturnAllVote() {
        //Data
        when(voteRepository.findAll())
                .thenReturn(singletonList(buildVotoDefault().build()));
        //Action
        List<Voto> allVotes = votingService.findAll();

        //Result
        assertThat(allVotes).isNotEmpty();
    }

    @Test
    public void shouldFindVotesByStaffId() {
        //Data
        List<Voto> votos = singletonList(buildVotoDefault().build());
        when(voteRepository.findByStaffId(1L))
                .thenReturn(Optional.of(votos));

        //Action
        List<Voto> result = votingService.findVotosByStaffId(1L);

        //Result
        assertThat(result).isEqualTo(votos);
    }

    @Test
    public void shouldThrowVoteNotFoundException() {
        //Action
        assertThatThrownBy(() -> votingService.findVotosByStaffId(1L))
                .isInstanceOf(VoteNotFoundException.class);

    }

    @Test
    public void shouldDelete() {
        //Data
        Voto voto = buildVotoDefault().build();
        when(voteRepository.findById(10L)).thenReturn(Optional.of(voto));

        //Action
        votingService.delete(voto);

        //Result
        verify(voteRepository, times(1)).delete(voto);
    }

    @Test
    public void shouldGetVoteResult() {
        //Data
        List<Voto> votos = singletonList(buildVotoDefault().build());
        VotingDto votingDto = buildVotingDtoDefault().build();
        when(voteRepository.findByStaffId(10L)).thenReturn(Optional.of(votos));
        when(sessaoRepository.countByStaffId(10L)).thenReturn(10L);

        //Action
        VotingDto result = votingService.getResultVotacao(10L);

        //Result
        assertThat(result).isEqualTo(votingDto);
    }
}
