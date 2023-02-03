package com.aledev.avaliacaotecnica.controller.impl;

import com.aledev.avaliacaotecnica.controller.VotingController;
import com.aledev.avaliacaotecnica.model.VotingDto;
import com.aledev.avaliacaotecnica.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "votings/")
@RequiredArgsConstructor
public class VotingControllerImpl implements VotingController {

	private final VotingService votacaoService;

	@GetMapping("v1/staffs/{id}/votings")
	@Override
	public VotingDto findVotosByPautaId(Long id) {
		return votacaoService.getResultVotacao(id);
	}
}
