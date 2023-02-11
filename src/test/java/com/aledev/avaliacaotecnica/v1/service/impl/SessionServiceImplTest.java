package com.aledev.avaliacaotecnica.v1.service.impl;

import com.aledev.avaliacaotecnica.builder.SessionBuilder;
import com.aledev.avaliacaotecnica.v1.entity.Session;
import com.aledev.avaliacaotecnica.v1.repository.SessionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Optional.of;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private SessionRepository sessionRepository;


    @Test
    public void shouldFindAllSession() {
        //Data
        List<Session> allSession = SessionBuilder.buildSessionDefault().buildList();
        when(sessionRepository.findAll()).thenReturn(allSession);
        //Action
        List<Session> response = sessionService.findAll();

        //Result
        Assertions.assertThat(response).isEqualTo(allSession);
        verify(sessionRepository, times(1)).findAll();
    }

    @Test
    public void shouldFindSessionById() {
        //Data
        Session session = SessionBuilder.buildSessionDefault().build();
        when(sessionRepository.findById(10L)).thenReturn(of(session));

        //Action
        Session response = sessionService.findSessionById(10L);

        //Result
        Assertions.assertThat(response).isEqualTo(session);
        verify(sessionRepository, times(1)).findById(10L);
    }


    @Test
    public void shouldDeleteSessionById() {
        //Data
        Session session = SessionBuilder.buildSessionDefault().build();
        when(sessionRepository.findById(10L)).thenReturn(of(session));

        //Action
        sessionService.deleteSessionById(10L);

        //Result
        verify(sessionRepository, times(1)).delete(session);
    }
}
