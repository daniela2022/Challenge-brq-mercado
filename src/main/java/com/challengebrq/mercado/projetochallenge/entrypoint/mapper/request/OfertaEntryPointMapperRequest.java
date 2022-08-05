package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaEntryPointMapperRequest {

    public static Produto converter(OfertaModelRequest ofertaModelRequest) {
        return Produto.builder()
                .id(ofertaModelRequest.getIdProduto())
                .porcentagemOferta(ofertaModelRequest.getPorcentagemOferta())
                .build();
    }

    public static List<Produto> convert(List<OfertaModelRequest> ofertas){
        List<Produto> produtos = new ArrayList<>();

        ofertas.forEach(oferta -> {
            produtos.add(converter((oferta)));
        });
        return produtos;
    }
}
