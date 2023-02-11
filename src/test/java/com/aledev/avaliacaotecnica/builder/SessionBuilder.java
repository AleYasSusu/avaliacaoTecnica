package com.aledev.avaliacaotecnica.builder;

import com.aledev.avaliacaotecnica.v1.entity.Session;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.aledev.avaliacaotecnica.builder.StaffBuilder.buildStaffDefault;

public class SessionBuilder {

    private Session session;

    public static SessionBuilder buildSessionDefault() {
        SessionBuilder builder = new SessionBuilder();
        builder.session = Session.builder()
                .staff(buildStaffDefault().build())
                .id(10L)
                .dataInicio(LocalDateTime.now())
                .minutosValidade(20L)
                .build();
        return builder;
    }

    public Session build() {
        return session;
    }

    public List<Session> buildList() {
        return Collections.singletonList(session);
    }
}
