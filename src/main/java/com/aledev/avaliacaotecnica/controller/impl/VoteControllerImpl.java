package com.aledev.avaliacaotecnica.controller.impl;


import com.aledev.avaliacaotecnica.controller.VoteController;
import com.aledev.avaliacaotecnica.entity.Voto;
import com.aledev.avaliacaotecnica.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "votes/")
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VotoService votoService;

    @GetMapping("v1/tariffs/sessions/votes")
    @Override
    public List<Voto> all() {
        return votoService.findAll();
    }

    @PostMapping("v1/tariffs/{staffId}/sessions/{sessionId}/votes")
    @Override
    public Voto createVoto(Long idPauta, Long idSessao, Voto voto) {
        return votoService.createVote(idPauta, idSessao, voto);
    }

    @GetMapping("v1/tariffs/sessions/votes/{id}")
    @Override
    public Voto findVotoById(Long id) {
        return votoService.findById(id);
    }

    @GetMapping("v1/tariffs/{id}/sessions/votes")
    @Override
    public List<Voto> findVotoBySessaoId(Long id) {

        return votoService.findVotesByStaffId(id);
    }

    @DeleteMapping("v1/tariffs/sessions/votes/{id}")
    @Override
    public void delete(Long id) {
        votoService.delete(id);
    }
}
