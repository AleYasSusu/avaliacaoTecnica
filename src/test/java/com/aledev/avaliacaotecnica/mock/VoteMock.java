package com.aledev.avaliacaotecnica.mock;

import com.aledev.avaliacaotecnica.v1.entity.Voto;

public class VoteMock {

    public static Voto buildVoto(){
        return Voto.builder()
                .cpf("1234")
                .id((long) 1)
                .escolha(true)
                .staff(StaffMock.buildVoto())
                .build();
    }
}
