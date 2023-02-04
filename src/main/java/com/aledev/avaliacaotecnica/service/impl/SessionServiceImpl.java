package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.exception.SessionNotFoundException;
import com.aledev.avaliacaotecnica.repository.SessionRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final StaffService staffService;


    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session createSession(Long id, Session sessao){
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
        var sessionById = sessionRepository.findById(id);
        if (!sessionById.isPresent()) {
            throw new SessionNotFoundException();
        }
        sessionRepository.delete(sessionById.get());
    }

    void deleteByStaffId(Long id) {
        var sessions = sessionRepository.findByStaffId(id);
        sessions.ifPresent(sessaoList -> sessaoList.forEach(sessionRepository::delete));
    }

    @Override
    public Session findSessionById(Long id) {
        var findById = sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException());
        return findById;
    }

    @Override
    public Session findByIdSessionAndStaffId(Long idSessao, Long pautaId) {
        var findByIdAndPautaId = sessionRepository.findByIdAndStaffId(idSessao, pautaId);
        if(!findByIdAndPautaId.isPresent()){
            throw new SessionNotFoundException();
        }
        return findByIdAndPautaId.get();
    }
}
