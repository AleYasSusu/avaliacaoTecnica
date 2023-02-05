package com.aledev.avaliacaotecnica.v1.service.impl;

import com.aledev.avaliacaotecnica.v1.entity.Staff;
import com.aledev.avaliacaotecnica.v1.exception.StaffNotFoundException;
import com.aledev.avaliacaotecnica.v1.repository.StaffRepository;
import com.aledev.avaliacaotecnica.v1.service.SessionService;
import com.aledev.avaliacaotecnica.v1.service.StaffService;
import com.aledev.avaliacaotecnica.v1.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private VoteService voteService;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public ResponseEntity<Staff> save(final Staff staff) {
        var staffCreated = staffRepository.save(staff);

        return new ResponseEntity<Staff>(staffCreated, HttpStatus.CREATED);
    }

    @Override
    public void delete(Long id) {
        var staffById = staffRepository.findById(id)
                .orElseThrow(StaffNotFoundException::new);
        staffRepository.delete(staffById);
    }

    @Override
    public Staff findById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(StaffNotFoundException::new);
    }
}
