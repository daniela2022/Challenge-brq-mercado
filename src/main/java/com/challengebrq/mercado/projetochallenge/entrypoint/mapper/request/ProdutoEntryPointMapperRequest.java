package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequestAtualizar;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import lombok.NoArgsConstructor;

import java.util.Map;

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

    public static Produto convert(String idProduto){
        return Produto.builder()
                .id(idProduto)
                .build();
    }

    public static Produto convert(String idProduto, ProdutoModelRequestAtualizar produtoModelRequestAtualizar) {

        return Produto.builder()
                .id(idProduto)
                .nome(produtoModelRequestAtualizar.getNomeProduto())
                .descricao(produtoModelRequestAtualizar.getDescricaoProduto())
                .marca(produtoModelRequestAtualizar.getMarcaProduto())
                .preco(produtoModelRequestAtualizar.getPrecoProduto())
                .ativo(produtoModelRequestAtualizar.getAtivoProduto())
                .ofertado(produtoModelRequestAtualizar.getOfertadoProduto())
                .porcentagemOferta(produtoModelRequestAtualizar.getPorcentagemOferta())
                .build();
    }
}
