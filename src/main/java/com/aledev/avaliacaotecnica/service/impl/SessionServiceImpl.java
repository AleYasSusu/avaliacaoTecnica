package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.exception.StaffNotFoundException;
import com.aledev.avaliacaotecnica.exception.SessionNotFoundException;
import com.aledev.avaliacaotecnica.repository.StaffRepository;
import com.aledev.avaliacaotecnica.repository.SessionRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final StaffRepository staffRepository;


    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }
    @Override
    public Session createSession(Long id, Session sessao){
        var findById = staffRepository.findById(id);
        if(!findById.isPresent()){
            throw new StaffNotFoundException();
        }
        sessao.setStaff(findById.get());
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

    void deleteByPautaId(Long id) {
        var sessions = sessionRepository.findByStaffId(id);
        sessions.ifPresent(sessaoList -> sessaoList.forEach(sessionRepository::delete));
    }
    @Override
    public Session findById(Long id) {
        var findById = sessionRepository.findById(id);
        if(!findById.isPresent()){
            throw new SessionNotFoundException();
        }
        return findById.get();
    }
    @Override
    public Session findByIdAndStaffId(Long idSessao, Long pautaId) {
        var findByIdAndPautaId = sessionRepository.findByIdAndStaffId(idSessao, pautaId);
        if(!findByIdAndPautaId.isPresent()){
            throw new SessionNotFoundException();
        }
        return findByIdAndPautaId.get();
    }
}
