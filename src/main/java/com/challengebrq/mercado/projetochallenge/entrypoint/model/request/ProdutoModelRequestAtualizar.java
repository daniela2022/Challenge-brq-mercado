package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoModelRequestAtualizar {

    @NotBlank
    private String idProduto;

    @NotNull
    @NotBlank
    private String nomeProduto;

    @NotBlank
    private String descricaoProduto;

    @NotBlank
    private String marcaProduto;


    private Double precoProduto;

    @NotBlank
    private Boolean ativoProduto;

    @NotBlank
    private Boolean ofertadoProduto;

    @NotNull
    @NotBlank
    private Integer porcentagemOferta;
}
