package com.aledev.avaliacaotecnica.service;

import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.model.VotingDto;

import java.util.List;

public interface VotingService {
    Voto save(Voto voto);

    void delete(Voto voto);

    List<Voto> findVotosByPautaId(Long id);

    VotingDto getResultVotacao(Long id);

    VotingDto buildVotingStaff(Long id);
}
