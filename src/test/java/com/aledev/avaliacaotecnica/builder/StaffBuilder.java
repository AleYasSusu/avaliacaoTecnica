package com.aledev.avaliacaotecnica.builder;

import com.aledev.avaliacaotecnica.v1.entity.Staff;

import java.util.List;

import static java.util.Collections.singletonList;

public class StaffBuilder {

    private Staff staff;

    public static StaffBuilder buildStaffDefault() {
        StaffBuilder builder = new StaffBuilder();
        builder.staff = Staff.builder()
                .id(10L)
                .nome("Test")
                .build();
        return builder;
    }

    public Staff build() {
        return staff;
    }

    public List<Staff> buildList() {
        return singletonList(staff);
    }
}
