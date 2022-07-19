package com.challengebrq.mercado.projetochallenge.usecase.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    private Long id;
    private String nome;
    private String descricao;
}
