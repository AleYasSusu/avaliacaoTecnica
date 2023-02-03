package com.aledev.avaliacaotecnica.service;

import com.aledev.avaliacaotecnica.entity.Voto;

import java.util.List;

public interface VotoService {
    Voto findById(Long id);

    Voto createVoto(Long idPauta, Long idSessao, Voto voto);

    List<Voto> findAll();

    void delete(Long id);

    void deleteByPautaId(Long id);

    List<Voto> findVotosByPautaId(Long id);
}
