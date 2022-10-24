package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.AtivacaoModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class AtivacaoEntryPointMapperRequest {


    public static Produto convert(AtivacaoModelRequest ativacaoModelRequest) {
        return Produto.builder()
                .id(ativacaoModelRequest.getId())
                .build();
    }

    public static List<Produto> convertList(List<AtivacaoModelRequest> ativacaoModelRequests){
        return ativacaoModelRequests.stream()
                .map(AtivacaoEntryPointMapperRequest::convert)
                .collect(Collectors.toList());
    }
}
