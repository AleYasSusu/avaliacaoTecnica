package com.aledev.avaliacaotecnica.v1.service;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.entity.Voto;

import java.util.List;

public interface VoteService {
    Voto findById(Long id);

    Voto createVote(Long idPauta, Long idSessao, Voto voto);

    void verifyVoto(Session session, Voto voto);

    void voteAlreadyExists(Voto voto);

    void cpfAbleToVote(Voto voto);

    List<Voto> findAll();

    void delete(Long id);

    void deleteByStaffId(Long id);

    List<Voto> findVotesByStaffId(Long id);
}