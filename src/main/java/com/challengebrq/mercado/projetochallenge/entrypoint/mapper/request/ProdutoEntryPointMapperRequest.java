package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelFiltroRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProdutoEntryPointMapperRequest {

    public static Produto converter(ProdutoModelRequest produtoModelRequest){
        return Produto.builder()
                .nome(produtoModelRequest.getNomeProduto())
                .descricao(produtoModelRequest.getDescricaoProduto())
                .marca(produtoModelRequest.getMarcaProduto())
                .preco(produtoModelRequest.getPrecoProduto())
                .build();
    }

    public static Produto convert(ProdutoModelFiltroRequest produtoModelFiltroRequest){
        return Produto.builder()
                .id(produtoModelFiltroRequest.getIdProduto())
                .nome(produtoModelFiltroRequest.getNomeProduto())
                .preco(produtoModelFiltroRequest.getPrecoProduto())
                .marca(produtoModelFiltroRequest.getMarcaProduto())
                .build();
    }

    public static Produto convert(String idProduto){
        return Produto.builder()
                .id(idProduto)
                .build();
    }
}
