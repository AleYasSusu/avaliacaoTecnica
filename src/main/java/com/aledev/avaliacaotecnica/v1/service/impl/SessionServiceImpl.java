package com.aledev.avaliacaotecnica.v1.service.impl;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.exception.SessionNotFoundException;
import com.aledev.avaliacaotecnica.v1.repository.SessionRepository;
import com.aledev.avaliacaotecnica.v1.service.SessionService;
import com.aledev.avaliacaotecnica.v1.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private StaffService staffService;

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }


    @Override
    public ResponseEntity<Session> createSession(Session sessao) {
        var sessionCreated = save(sessao);
        return new ResponseEntity<Session>(sessionCreated, HttpStatus.CREATED);
    }

    private Session save(final Session session) {
        if (session.getDataInicio() == null) {
            session.setDataInicio(LocalDateTime.now());
        }
        if (session.getMinutosValidade() == null) {
            session.setMinutosValidade(1L);
        }
        return sessionRepository.save(session);

    }

    @Override
    public void deleteSessionById(Long id) {
        var sessionById =
                sessionRepository.findById(id)
                        .orElseThrow(SessionNotFoundException::new);
        sessionRepository.delete(sessionById);
    }

    @Override
    public void deleteByStaffId(Long id) {
        var sessions = sessionRepository.findByStaffId(id);
        sessions.ifPresent(sessaoList -> sessaoList.forEach(sessionRepository::delete));
    }

    @Override
    public Session findSessionById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(SessionNotFoundException::new);
    }

    @Override
    public Session findByIdSessionAndStaffId(Long idSessao, Long pautaId) {
        return sessionRepository.findByIdAndStaffId(idSessao, pautaId)
                .orElseThrow(SessionNotFoundException::new);
    }
}
