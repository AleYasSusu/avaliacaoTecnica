package com.aledev.avaliacaotecnica.v1.controller;

import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.entity.Staff;
import com.aledev.avaliacaotecnica.v1.model.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface StaffController {
    @Operation(
            summary = "Endpoint interno para retornar uma lista com todas as pautas",
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
    List<Staff> all();

    @Operation(
            summary = "Endpoint para criar uma pauta",
            responses = {
                    @ApiResponse(
                            description = "Lista obtida com sucesso.",
                            responseCode = "200",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    array =
                                    @ArraySchema(
                                            schema =
                                            @Schema(
                                                    implementation =
                                                            Map.class)))),
                    @ApiResponse(
                            description =
                                    "A Requisição foi mal formada, omitindo atributos obrigatórios, seja no payload ou através de atributos na url.",
                            responseCode = "400",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            description = "Erro mapeado.",
                            responseCode = "500",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class)))
            })
    ResponseEntity<Staff> create(@RequestBody Staff staff);

    @Operation(
            summary = "Endpoint interno para retornar uma pauta por meio do id da pauta.",
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
    Staff findById(@PathVariable Long id);

    @Operation(
            summary = "Endpoint interno para deletar uma pauta por meio do id da pauta.",
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
    void delete(@PathVariable Long id);
}
