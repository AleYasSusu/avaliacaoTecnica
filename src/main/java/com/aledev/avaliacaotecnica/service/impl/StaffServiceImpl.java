package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Staff;
import com.aledev.avaliacaotecnica.exception.StaffNotFoundException;
import com.aledev.avaliacaotecnica.repository.StaffRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.StaffService;
import com.aledev.avaliacaotecnica.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private VotoService votoService;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff save(final Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        var staffById = staffRepository.findById(id)
                .orElseThrow(StaffNotFoundException::new);
        staffRepository.delete(staffById);
        sessionService.deleteSessionById(id);
        votoService.deleteByStaffId(id);
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(StaffNotFoundException::new);
    }
}
