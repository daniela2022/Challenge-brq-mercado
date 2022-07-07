package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

public class ProdutoRequestMapper {

    public static ProdutoEntity converter(Produto produto) {
        return ProdutoEntity.builder()
                .idProduto(produto.getId())
                .nomeProduto(produto.getNome())
                .descricaoProduto(produto.getDescricao())
                .marcaProduto(produto.getMarca())
                .precoProduto(produto.getPreco())
                .dataCadastro(produto.getDataCadastro())
                .produtoAtivo(produto.getAtivo())
                .produtoOfertado(produto.getOfertado())
                .produtoPorcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }
}