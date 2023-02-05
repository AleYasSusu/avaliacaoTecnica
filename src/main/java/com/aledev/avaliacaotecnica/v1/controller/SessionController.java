package com.aledev.avaliacaotecnica.v1.controller;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Validated
public interface SessionController {


    List<Session> all();

    @Operation(
            summary = "Endpoint interno para retornar uma lista com todas as sessões",
            responses = {
                    @ApiResponse(
                            description = "Dados obtidos com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Session.class))),

                    @ApiResponse(
                            description = "Erro nao mapeado.",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)))
            })
    ResponseEntity<Session> createSession(@RequestBody Session session);

    @Operation(
            summary = "Endpoint interno para retornar uma  sessões por id.",
            responses = {
                    @ApiResponse(
                            description = "Dados obtidos com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Session.class))),

                    @ApiResponse(
                            description = "Erro nao mapeado.",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)))
            })
    Session findSessaoById(@PathVariable Long id);

    @Operation(
            summary = "Endpoint interno para retornar uma  sessões por " +
                    "meio do id da sessao e do aid da pauta.",
            responses = {
                    @ApiResponse(
                            description = "Dados obtidos com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Session.class))),

                    @ApiResponse(
                            description = "Erro nao mapeado.",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Object.class)))
            })
    Session findSessaoByIdAndStaffId(@PathVariable Long id, @PathVariable Long idSessao);
}
