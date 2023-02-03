package com.aledev.avaliacaotecnica.service;

import com.aledev.avaliacaotecnica.entity.Staff;

import java.util.List;

public interface StaffService {
    List<Staff> findAll();

    Staff save(Staff staff);

    void delete(Long id);

    Staff findById(Long id);
}
