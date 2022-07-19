package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepartamentoModelRequest {

    private String nomeDepartamento;
    private String descricaoDepartamento;
}
