package com.challengebrq.mercado.projetochallenge.usecase.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private String id;
    private String nome;
    private String descricao;
    private String marca;
    private Double preco;
    private String dataCadastro;
    private String dataAtualizacao;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagemOferta;
    private List<Departamento> departamentos;
}
