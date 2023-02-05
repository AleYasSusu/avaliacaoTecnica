package com.aledev.avaliacaotecnica.v1.repository;


import com.aledev.avaliacaotecnica.v1.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface VoteRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByCpf(String cpf);

    Optional<List<Voto>> findByStaffId(Long id);

    Optional<Voto> findByCpfAndStaffId(String cpf, Long id);
}
