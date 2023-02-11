package com.aledev.avaliacaotecnica.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class SessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeAll
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext).build();
    }

}
