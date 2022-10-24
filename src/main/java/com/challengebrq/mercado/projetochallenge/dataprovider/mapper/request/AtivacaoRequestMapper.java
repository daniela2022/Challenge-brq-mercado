package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

public class AtivacaoRequestMapper {

    public static ProdutoEntity converter(Produto produto) {
        return ProdutoEntity.builder()
                .idProduto(produto.getId())
                .build();
    }


    public static List<ProdutoEntity> convert(List<Produto> produtos){
        List<ProdutoEntity> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converter((produto)));
        });
        return produtosModelResponse;
    }
}
