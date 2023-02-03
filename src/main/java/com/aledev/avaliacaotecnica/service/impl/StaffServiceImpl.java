package com.aledev.avaliacaotecnica.service.impl;

import com.aledev.avaliacaotecnica.entity.Staff;
import com.aledev.avaliacaotecnica.exception.StaffNotFoundException;
import com.aledev.avaliacaotecnica.repository.StaffRepository;
import com.aledev.avaliacaotecnica.service.SessionService;
import com.aledev.avaliacaotecnica.service.StaffService;
import com.aledev.avaliacaotecnica.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final SessionService sessaoService;
    private final VotoService votoService;


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
        var staffById = staffRepository.findById(id);
        if (!staffById.isPresent()) {
            throw new StaffNotFoundException();
        }
        staffRepository.delete(staffById.get());
        sessaoService.deleteSessionById(id);
        votoService.deleteByStaffId(id);
    }

    @Override
    public Staff findById(Long id) {
       var findById = staffRepository.findById(id);
        if (!findById.isPresent()) {
            throw new StaffNotFoundException();
        }
        return findById.get();
    }
}
