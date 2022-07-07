package com.challengebrq.mercado.projetochallenge.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProdutoModelResponse {

    private String idProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String marcaProduto;
    private Double precoProduto;
    private String dataCadastro;
    private String dataAtualizacao;
    private Boolean ativoProduto;
    private Boolean ofertadoProduto;
    private Integer porcentagemOferta;
}
