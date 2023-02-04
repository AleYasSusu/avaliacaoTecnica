package com.aledev.avaliacaotecnica.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_sessao")
public class Session implements Serializable {
    @Id
    @SequenceGenerator(name = "sessao_seq", sequenceName = "sessao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq")
    private Long id;

    private LocalDateTime dataInicio;

    private Long minutosValidade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Staff staff;

    public Session pauta(Staff staff) {
        this.staff = staff;
        return this;
    }
}
