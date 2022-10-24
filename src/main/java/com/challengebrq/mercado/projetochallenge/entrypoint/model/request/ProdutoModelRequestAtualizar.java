package com.challengebrq.mercado.projetochallenge.entrypoint.model.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.utils.RemoveEspacoString;
import com.challengebrq.mercado.projetochallenge.entrypoint.validation.IntegerEmBrancoValidation;
import com.challengebrq.mercado.projetochallenge.entrypoint.validation.TextoEmBrancoValidation;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProdutoModelRequestAtualizar {

    @Size(max = 60)
    @JsonDeserialize(using = RemoveEspacoString.class)
    @TextoEmBrancoValidation
    private String nome;

    @Size(max = 256)
    @JsonDeserialize(using = RemoveEspacoString.class)
    @TextoEmBrancoValidation
    private String descricao;

    @Size(max = 40)
    @JsonDeserialize(using = RemoveEspacoString.class)
    @TextoEmBrancoValidation
    private String marca;

    @Positive
    private Double preco;

   @Valid
    List<Integer> idsDepartamento;
}
