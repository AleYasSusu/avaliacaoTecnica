package com.aledev.avaliacaotecnica.v1.service;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    ResponseEntity<Session> createSession(Session sessao);

    void deleteSessionById(Long id);

    void deleteByStaffId(Long id);

    Session findSessionById(Long id);

    Session findByIdSessionAndStaffId(Long idSessao, Long pautaId);
}
