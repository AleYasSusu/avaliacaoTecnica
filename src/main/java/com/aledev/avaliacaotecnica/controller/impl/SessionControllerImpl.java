package com.aledev.avaliacaotecnica.controller.impl;


import com.aledev.avaliacaotecnica.controller.SessionController;
import com.aledev.avaliacaotecnica.entity.Session;
import com.aledev.avaliacaotecnica.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "sessions/")
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {

    private final SessionService sessionService;

    @Override
    @GetMapping("v1/pautas/sessoes")
    public List<Session> all() {
        return sessionService.findAll();
    }

    @Override
    @PostMapping("v1/pautas/{id}/sessoes")
    public Session createSession(Long id, Session session) {
        return sessionService.createSession(id, session);
    }

    @Override
    @GetMapping("v1/pautas/sessoes/{id}")
    public Session findSessaoById(@PathVariable Long id) {
        return sessionService.findSessionById(id);
    }

    @Override
    @GetMapping("v1/pautas/{id}/sessoes/{idSessao}")
    public Session findSessaoByIdAndStaffId(Long id, Long idSessao) {
        return sessionService.findByIdSessionAndStaffId(idSessao, id);
    }


    @DeleteMapping("v1/pautas/sessoes/{id}")
    public void delete(Long id) {
        sessionService.deleteSessionById(id);
    }
}
