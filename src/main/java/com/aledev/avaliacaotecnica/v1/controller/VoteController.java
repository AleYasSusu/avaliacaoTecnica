package com.aledev.avaliacaotecnica.v1.controller;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.entity.Voto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface VoteController {
    @Operation(
            summary = "Endpoint interno para retornar todos os votos de uma pauta.",
            responses = {
                    @ApiResponse(
                            description = "Dados obtidos com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Session.class))),
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
    List<Voto> all();

    @Operation(
            summary = "Endpoint interno para criar um voto com o id da pauta e o id da sessão.",
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
    Voto createVoto(@PathVariable Long idPauta, @PathVariable Long idSessao, @RequestBody Voto voto);

    @Operation(
            summary = "Endpoint interno para retornar um voto por meio do Id.",
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
    Voto findVotoById(@PathVariable Long id);

    @Operation(
            summary = "Endpoint interno para retornar uma lista de voto por meio do Id da sessão.",
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
    List<Voto> findVotoBySessaoId(@PathVariable Long id);

    @Operation(
            summary = "Endpoint interno para deletar votos.",
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
    void delete(@PathVariable Long id);
}
