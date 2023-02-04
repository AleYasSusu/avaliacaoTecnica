package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.exception.SessionNotFoundException;
import com.aledev.avaliacaotecnica.repository.SessionRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Session createSession(Long id, Session sessao) {
        var findStaffById = staffService.findById(id);
        sessao.setStaff(findStaffById);
        return save(sessao);
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
