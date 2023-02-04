package com.aledev.avaliacaotecnica.service;

import com.aledev.avaliacaotecnica.entity.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    Session createSession(Long id, Session sessao);

    void deleteSessionById(Long id);

    Session findSessionById(Long id);

    Session findByIdSessionAndStaffId(Long idSessao, Long pautaId);
}
