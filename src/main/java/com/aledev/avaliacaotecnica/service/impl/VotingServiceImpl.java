package com.aledev.avaliacaotecnica.service.impl;


import com.aledev.avaliacaotecnica.entity.Staff;
import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.exception.BusinessException;
import com.aledev.avaliacaotecnica.exception.VotingNotFoundException;
import com.aledev.avaliacaotecnica.exception.VoteNotFoundException;
import com.aledev.avaliacaotecnica.model.VotingDto;
import com.aledev.avaliacaotecnica.repository.SessionRepository;
import com.aledev.avaliacaotecnica.repository.VoteRepository;
import com.aledev.avaliacaotecnica.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Voto> votoById = voteRepository.findById(voto.getId());
        if (!votoById.isPresent()) {
            throw new VoteNotFoundException();
        }
        voteRepository.delete(voto);
    }
    @Override
    public List<Voto> findVotosByPautaId(Long id) {
        Optional<List<Voto>> findByPautaId = voteRepository.findByStaffId(id);

        if (!findByPautaId.isPresent()) {
            throw new VoteNotFoundException();
        }

        return findByPautaId.get();
    }
    @Override
    public VotingDto getResultVotacao(Long id) {
        VotingDto votacaoPauta = buildVotingStaff(id);
        //kafkaSender.sendMessage(votacaoPauta);
        return votacaoPauta;
    }

    @Override
    public VotingDto buildVotingStaff(Long id) {
        var votosByStaff = voteRepository.findByStaffId(id);
        if (!votosByStaff.isPresent() || votosByStaff.get().isEmpty()) {
            throw new VotingNotFoundException();
        }

        Staff staff = votosByStaff.get().iterator().next().getStaff();

        Long totalSessions = sessaoRepository.countByStaffId(staff.getId());


        Integer total = votosByStaff.get().size();

        Integer totalVoteYes = (int) votosByStaff.get().stream().filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
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
