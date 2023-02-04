package com.aledev.avaliacaotecnica.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_staff")
public class Staff implements Serializable {
    @Id
    @SequenceGenerator(name = "staff_seq", sequenceName = "staff_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_seq")
    private Long id;

    private String nome;
}
