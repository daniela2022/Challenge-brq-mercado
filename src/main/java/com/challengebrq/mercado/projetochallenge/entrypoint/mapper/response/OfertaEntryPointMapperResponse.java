package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.OfertaModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaEntryPointMapperResponse {

    public static OfertaModelResponse converterOfertaParaModel(Produto produto) {
        return OfertaModelResponse.builder()
                .idProduto(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .dataAtualizacao(produto.getDataAtualizacao())
                .build();
    }

    public static List<OfertaModelResponse> convert(List<Produto> produtos){
        List<OfertaModelResponse> ofertasModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            ofertasModelResponse.add(converterOfertaParaModel((produto)));
        });
        return ofertasModelResponse;
    }
}
