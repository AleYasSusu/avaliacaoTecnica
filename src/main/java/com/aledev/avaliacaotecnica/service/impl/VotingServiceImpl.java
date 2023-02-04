package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Staff;
import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.exception.BusinessException;
import com.aledev.avaliacaotecnica.exception.VoteNotFoundException;
import com.aledev.avaliacaotecnica.exception.VotingNotFoundException;
import com.aledev.avaliacaotecnica.model.VotingDto;
import com.aledev.avaliacaotecnica.repository.SessionRepository;
import com.aledev.avaliacaotecnica.repository.VoteRepository;
import com.aledev.avaliacaotecnica.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VotingServiceImpl implements VotingService {

    private final VoteRepository voteRepository;

    private final SessionRepository sessaoRepository;


    @Override
    public Voto save(final Voto voto) {
        verifyIfExists(voto);
        return voteRepository.save(voto);
    }

    private void verifyIfExists(final Voto voto) {
        var votoByCpfAndPauta = voteRepository.findByCpf(voto.getCpf());

        if (votoByCpfAndPauta.isPresent() && (voto.isNew() || isNotUnique(voto, votoByCpfAndPauta.get()))) {
            throw new BusinessException(null, null);
        }
    }

    private boolean isNotUnique(Voto voto, Voto votoByCpfAndPauta) {
        return voto.alreadyExist() && !votoByCpfAndPauta.equals(voto);
    }

    public List<Voto> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public void delete(Voto voto) {
        var votoById =
                voteRepository.findById(voto.getId())
                        .orElseThrow(VoteNotFoundException::new);
        voteRepository.delete(votoById);
    }

    @Override
    public List<Voto> findVotosByPautaId(Long id) {
        return voteRepository.findByStaffId(id)
                        .orElseThrow(VoteNotFoundException::new);
    }

    @Override
    public VotingDto getResultVotacao(Long id) {
        return buildVotingStaff(id);
    }

    @Override
    public VotingDto buildVotingStaff(Long id) {
        var votosByStaff =
                voteRepository.findByStaffId(id)
                        .orElseThrow(VotingNotFoundException::new);

        Staff staff = votosByStaff.iterator().next().getStaff();

        Long totalSessions = sessaoRepository.countByStaffId(staff.getId());

        Integer total = votosByStaff.size();

        Integer totalVoteYes = (int) votosByStaff.stream().filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
                .count();

        Integer totalVoteNo = total - totalVoteYes;

        return VotingDto.builder()
                .staff(staff)
                .totalVotes(total)
                .totalSessions(totalSessions.intValue())
                .totalVoteYes(totalVoteYes)
                .totalVoteNo(totalVoteNo)
                .build();
    }
}
