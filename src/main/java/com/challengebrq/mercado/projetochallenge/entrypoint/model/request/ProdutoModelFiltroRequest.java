package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoModelFiltroRequest {

    private String idProduto;
    private String nomeProduto;
    private String marcaProduto;
    private Double precoProduto;
}
