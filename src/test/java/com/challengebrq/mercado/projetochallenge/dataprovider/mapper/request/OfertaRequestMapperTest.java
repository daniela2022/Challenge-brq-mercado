package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfertaRequestMapperTest {

    @Test
    void testeConverteRequestSucesso(){
        Produto mockProduto = mockProduto();

        ProdutoEntity produtoEntity = OfertaRequestMapper.converter(mockProduto);

        assertNotNull(produtoEntity);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoEntity.getIdProduto()),
                () -> assertEquals(10, produtoEntity.getProdutoPorcentagemOferta())
        );
    }

    @Test
    void testeConverteListaRequestSucesso(){
        var mockProduto = mockProduto();

        List<ProdutoEntity> produtos = OfertaRequestMapper.convert(List.of(mockProduto));

        assertNotNull(produtos);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtos.get(0).getIdProduto()),
                () -> assertEquals(10, produtos.get(0).getProdutoPorcentagemOferta())
        );
    }

    private Produto mockProduto(){
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .porcentagemOferta(10)
                .build();
    }

}