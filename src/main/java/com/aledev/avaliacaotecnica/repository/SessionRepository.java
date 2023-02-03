package com.aledev.avaliacaotecnica.repository;


import com.aledev.avaliacaotecnica.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByIdAndStaffId(Long id, Long pautaId);

    Long countByStaffId(Long id);

    Optional<List<Session>> findByStaffId(Long id);
}
