package com.challengebrq.mercado.projetochallenge.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "departamentos")
public class DepartamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricaoDepartamento;

}
