package com.aledev.avaliacaotecnica;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AvaliacaoTecnicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvaliacaoTecnicaApplication.class, args);
    }

}
