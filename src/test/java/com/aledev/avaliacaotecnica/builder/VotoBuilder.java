package com.aledev.avaliacaotecnica.builder;

import com.aledev.avaliacaotecnica.v1.entity.Voto;

import static com.aledev.avaliacaotecnica.builder.StaffBuilder.buildStaffDefault;

public class VotoBuilder {

    private Voto voto;

    public static VotoBuilder buildVotoDefault() {
        VotoBuilder builder = new VotoBuilder();
        builder.voto = Voto.builder()
                .id(10L)
                .cpf("999.999.999-99")
                .escolha(true)
                .staff(buildStaffDefault().build())
                .build();
        return builder;
    }

    public Voto build() {
        return voto;
    }
}
