package com.aledev.avaliacaotecnica.service;

import com.aledev.avaliacaotecnica.entity.Voto;

import java.util.List;

public interface VotoService {
    Voto findById(Long id);

    Voto createVote(Long idPauta, Long idSessao, Voto voto);

    List<Voto> findAll();

    void delete(Long id);

    void deleteByStaffId(Long id);

    List<Voto> findVotesByStaffId(Long id);
}
