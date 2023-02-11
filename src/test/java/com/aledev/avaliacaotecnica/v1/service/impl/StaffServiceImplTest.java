package com.aledev.avaliacaotecnica.v1.service.impl;

import com.aledev.avaliacaotecnica.v1.entity.Staff;
import com.aledev.avaliacaotecnica.v1.repository.StaffRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.aledev.avaliacaotecnica.builder.StaffBuilder.buildStaffDefault;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StaffServiceImplTest {

    @InjectMocks
    private StaffServiceImpl staffService;

    @Mock
    private StaffRepository staffRepository;


    @Test
    public void shouldSaveStaff() {
        //Data
        Staff staff = buildStaffDefault().build();
        ResponseEntity<Staff> expected = new ResponseEntity<>(staff, HttpStatus.CREATED);

        when(staffRepository.save(staff)).thenReturn(staff);

        //Action
        ResponseEntity<Staff> save = staffService.save(staff);

        //Result
        verify(staffRepository, times(1))
                .save(staff);
        assertThat(save).isEqualTo(expected);
    }

    @Test
    public void shouldFindAllStaff() {
        //Data
        List<Staff> staffList = buildStaffDefault().buildList();

        when(staffRepository.findAll()).thenReturn(staffList);
        //Action
        List<Staff> allStaff = staffService.findAll();

        //Result
        assertThat(allStaff).isEqualTo(staffList);
    }

    @Test
    public void shouldFindStaffById() {
        //Data
        Staff staff = buildStaffDefault().build();
        when(staffRepository.findById(10L)).thenReturn(of(staff));
        //Action
        Staff result = staffService.findById(10L);
        //Result
        assertThat(result).isEqualTo(staff);
    }

    @Test
    public void shouldDeleteStaff() {
        //Data
        Staff staff = buildStaffDefault().build();

        when(staffRepository.findById(10L)).thenReturn(of(staff));

        //Action
        staffService.delete(10L);

        //Result
        verify(staffRepository, times(1)).delete(staff);
    }
}

