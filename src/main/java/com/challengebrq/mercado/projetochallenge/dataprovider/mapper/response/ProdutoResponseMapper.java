package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoResponseMapper {

    public static Produto converter(ProdutoEntity produtoEntity){
        return Produto.builder()
                .id(produtoEntity.getIdProduto())
                .nome(produtoEntity.getNomeProduto())
                .descricao(produtoEntity.getDescricaoProduto())
                .marca(produtoEntity.getMarcaProduto())
                .preco(produtoEntity.getPrecoProduto())
                .dataCadastro(produtoEntity.getDataCadastro())
                .dataAtualizacao(produtoEntity.getDataAtualizacao())
                .ativo(produtoEntity.getProdutoAtivo())
                .ofertado(produtoEntity.getProdutoOfertado())
                .porcentagemOferta(produtoEntity.getProdutoPorcentagemOferta())
                .build();
    }

    public static Produto converterLista(ProdutoEntity produtoEntity){
        return Produto.builder()
                .id(produtoEntity.getIdProduto())
                .nome(produtoEntity.getNomeProduto())
                .marca(produtoEntity.getMarcaProduto())
                .preco(produtoEntity.getPrecoProduto())
                .build();
    }

    public static List<Produto> convert(List<ProdutoEntity> produtoEntities){
        return produtoEntities.stream()
                .map(ProdutoResponseMapper::converterLista)
                .collect(Collectors.toList());
    }

}
