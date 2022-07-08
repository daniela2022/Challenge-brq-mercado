package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.List;

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

    public static List<Produto> convert(List<ProdutoEntity> produtos){
        List<Produto> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converter((produto)));
        });
        return produtosModelResponse;
    }
}
