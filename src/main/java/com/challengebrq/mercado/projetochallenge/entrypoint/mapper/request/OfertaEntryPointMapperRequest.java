package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequestRemover;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class OfertaEntryPointMapperRequest {

    public static Produto converter(OfertaModelRequest ofertaModelRequest) {
        return Produto.builder()
                .id(ofertaModelRequest.getId())
                .porcentagemOferta(ofertaModelRequest.getPorcentagemOferta())
                .build();
    }

    public static Produto converterDelete(OfertaModelRequestRemover ofertaModelRequest){
        return Produto.builder()
                .id(ofertaModelRequest.getId())
                .build();
    }

    public static List<Produto> convertLista(List<OfertaModelRequestRemover> ofertas){
        List<Produto> produtos = new ArrayList<>();

        ofertas.forEach(oferta -> {
            produtos.add(converterDelete((oferta)));
        });
        return produtos;
    }

    public static List<Produto> convert(List<OfertaModelRequest> ofertas){
        List<Produto> produtos = new ArrayList<>();

        ofertas.forEach(oferta -> {
            produtos.add(converter((oferta)));
        });
        return produtos;
    }
}
