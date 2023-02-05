package com.aledev.avaliacaotecnica.v1.controller.impl;


import com.aledev.avaliacaotecnica.v1.controller.SessionController;
import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "sessions/")
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {

    private final SessionService sessionService;

    @Override
    @GetMapping("v1/staffs/sessions")
    public List<Session> all() {
        return sessionService.findAll();
    }

    @Override
    @PostMapping("v1/sessions")
    public ResponseEntity<Session> createSession(Session session) {
        return sessionService.createSession(session);
    }

    @Override
    @GetMapping("v1/staffs/sessions/{id}")
    public Session findSessaoById(@PathVariable Long id) {
        return sessionService.findSessionById(id);
    }

    @Override
    @GetMapping("v1/staffs/{id}/sessions/{idSessao}")
    public Session findSessaoByIdAndStaffId(Long id, Long idSessao) {
        return sessionService.findByIdSessionAndStaffId(idSessao, id);
    }


    @DeleteMapping("v1/staffs/sessions/{id}")
    public void delete(Long id) {
        sessionService.deleteSessionById(id);
    }
}
