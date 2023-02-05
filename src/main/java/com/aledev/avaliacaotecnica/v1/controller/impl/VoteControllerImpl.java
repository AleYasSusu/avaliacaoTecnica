package com.aledev.avaliacaotecnica.v1.controller.impl;


import com.aledev.avaliacaotecnica.v1.controller.VoteController;
import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "votes/")
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final VoteService voteService;

    @GetMapping("v1/staffs/sessions/votes")
    @Override
    public List<Voto> all() {
        return voteService.findAll();
    }

    @Override
    @PostMapping("v1/pautas/{idPauta}/sessoes/{idSessao}/votos")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Voto createVoto(@PathVariable Long idPauta, @PathVariable Long idSessao, @RequestBody Voto voto) {
        return voteService.createVote(idPauta, idSessao, voto);
    }

    @GetMapping("v1/staffs/sessions/votes/{id}")
    @Override
    public Voto findVotoById(Long id) {
        return voteService.findById(id);
    }

    @GetMapping("v1/staffs/{id}/sessions/votes")
    @Override
    public List<Voto> findVotoBySessaoId(Long id) {

        return voteService.findVotesByStaffId(id);
    }

    @DeleteMapping("v1/staffs/sessions/votes/{id}")
    @Override
    public void delete(Long id) {
        voteService.delete(id);
    }
}
