package com.aledev.avaliacaotecnica.v1.controller;

import com.aledev.avaliacaotecnica.v1.entity.Voto;
import com.aledev.avaliacaotecnica.v1.model.VotingDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface VotingController {
    @Operation(
            summary = "Endpoint interno para retornar os votos de uma pauta por meio do  id da pauta.",
            responses = {
                    @ApiResponse(
                            description = "Dados obtidos com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Voto.class))),
                    @ApiResponse(
                            description =
                                    "A Requisição foi mal formada, omitindo atributos obrigatórios, seja no payload ou através de atributos na url.",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),

                    @ApiResponse(
                            description = "Erro nao mapeado.",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)))
            })
    VotingDto findVotosByPautaId(@PathVariable Long id);
}
