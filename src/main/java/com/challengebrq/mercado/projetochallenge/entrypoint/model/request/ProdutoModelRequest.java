package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.utils.RemoveEspacoString;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@Setter
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoModelRequest {

    @NotBlank
    @Size(max = 60)
    @JsonDeserialize(using = RemoveEspacoString.class)
    private String nome;

    @NotBlank
    @Size(max = 256)
    @JsonDeserialize(using = RemoveEspacoString.class)
    private String descricao;

    @NotBlank
    @Size(max = 40)
    @JsonDeserialize(using = RemoveEspacoString.class)
    private String marca;

    @NotNull
    @Positive
    private Double preco;

    @Valid
    @NotEmpty
    @NotNull
    private List<Integer> codigosDepartamento;

}
