package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFilter {

    private String nome;
    private String marca;
    private Double preco;
    private Integer departamentoId;
}
