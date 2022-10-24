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
public class OfertaModelResponse {

    private String idProduto;
    private String nome;
    private String marca;
    private Double preco;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private String dataAtualizacao;
}
