package com.challengebrq.mercado.projetochallenge.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

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
    private String nomeDepartamento;

    @Column(nullable = false)
    private String descricaoDepartamento;
}
