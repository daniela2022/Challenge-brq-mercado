package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.AtivacaoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class AtivacaoEntryPointMapperResponse {

    public static AtivacaoModelResponse convert(Produto produto) {
        return AtivacaoModelResponse.builder()
                .idProduto(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ativo(produto.getAtivo())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .dataAtualizacao(produto.getDataAtualizacao())
                .build();
    }

    public static List<AtivacaoModelResponse> convertList(List<Produto> produtos){
        return produtos.stream()
                .map(AtivacaoEntryPointMapperResponse::convert)
                .collect(Collectors.toList());
    }
}
