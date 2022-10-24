package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.utils.RemoveEspacoString;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepartamentoModelRequest {

    @NotBlank
    @JsonDeserialize(using = RemoveEspacoString.class)
    @Size(max = 50)
    private String nome;

    @NotBlank
    @JsonDeserialize(using = RemoveEspacoString.class)
    @Size(max = 256)
    private String descricao;
}
