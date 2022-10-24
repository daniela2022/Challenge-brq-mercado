package com.challengebrq.mercado.projetochallenge.usecase.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    private Integer id;
    private String nome;
    private String descricao;
    //private List<Integer> codigosDepartamento;
}
