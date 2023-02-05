package com.aledev.avaliacaotecnica.mock;

import com.aledev.avaliacaotecnica.v1.entity.Staff;

public class StaffMock {
    public static Staff buildVoto(){
        return Staff.builder()
                .id(1L)
                .nome("teste ti")
                .build();
    }

}
