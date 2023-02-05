package com.aledev.avaliacaotecnica.v1.service;

import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.model.VotingDto;

import java.util.List;

public interface VotingService {
    Voto save(Voto voto);

    void delete(Voto voto);

    List<Voto> findVotosByStaffId(Long id);

    VotingDto getResultVotacao(Long id);

    VotingDto buildVotingStaff(Long id);
}
