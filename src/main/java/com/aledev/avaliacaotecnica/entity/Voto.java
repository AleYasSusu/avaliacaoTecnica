package com.aledev.avaliacaotecnica.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity(name = "tbl_vote")
@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Voto implements Serializable {
    @Id
    @SequenceGenerator(name = "sessao_seq", sequenceName = "sessao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq")
    private Long id;

    @NotBlank(message = "voto-1")
    private String cpf;

    @NotNull(message = "voto-2")
    private Boolean escolha;

    @NotNull(message = "voto-3")
    @ManyToOne(fetch = FetchType.EAGER)
    private Staff staff;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    @JsonIgnore
    public boolean alreadyExist() {
        return getId() != null;
    }
}

