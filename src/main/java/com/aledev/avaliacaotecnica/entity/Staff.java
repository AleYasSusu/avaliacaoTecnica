package com.aledev.avaliacaotecnica.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "tbl_staff")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {
    @Id
    @SequenceGenerator(name = "staff_seq", sequenceName = "staff_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq")
    private Long id;

    private String nome;
}
