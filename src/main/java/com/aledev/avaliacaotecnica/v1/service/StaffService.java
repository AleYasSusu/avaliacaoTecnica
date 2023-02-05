package com.aledev.avaliacaotecnica.v1.service;

import com.aledev.avaliacaotecnica.v1.entity.Staff;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StaffService {
    List<Staff> findAll();

    ResponseEntity<Staff> save(Staff staff);

    void delete(Long id);

    Staff findById(Long id);
}
