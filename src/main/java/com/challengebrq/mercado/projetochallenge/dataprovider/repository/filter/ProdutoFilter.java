package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ProdutoFilter {

    private String idProduto;
    private String nomeProduto;
    private String marcaProduto;
    private Double precoProduto;
}
