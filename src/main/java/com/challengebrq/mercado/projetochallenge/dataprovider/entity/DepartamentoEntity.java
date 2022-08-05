package com.challengebrq.mercado.projetochallenge.dataprovider.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartamento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricaoDepartamento;
}
