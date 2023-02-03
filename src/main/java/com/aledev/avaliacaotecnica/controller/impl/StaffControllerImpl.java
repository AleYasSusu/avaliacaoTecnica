package com.aledev.avaliacaotecnica.controller.impl;

import com.aledev.avaliacaotecnica.controller.StaffController;
import com.aledev.avaliacaotecnica.entity.Staff;
import com.aledev.avaliacaotecnica.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "tariffs/")
@RequiredArgsConstructor
public class StaffControllerImpl implements StaffController {

    private final StaffService staffService;

    @Override
    @GetMapping("v1/tariffs")
    public List<Staff> all() {
        return staffService.findAll();
    }

    @Override
    @PostMapping("v1/tariffs")
    public Staff create(@RequestBody Staff staff) {
        return staffService.save(staff);
    }

    @Override
    @GetMapping("v1/tariffs/{id}")
    public Staff findById(@PathVariable Long id) {
        return staffService.findById(id);
    }

    @Override
    @DeleteMapping("v1/tariffs/{id}")
    public void delete(@PathVariable Long id) {
        staffService.delete(id);
    }
}
