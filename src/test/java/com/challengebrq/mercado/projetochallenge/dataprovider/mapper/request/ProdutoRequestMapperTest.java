package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoRequestMapperTest {

    @Test
    void testeConverteRequestSucesso(){
        Produto mockProduto = mockProduto();

        ProdutoEntity produtoEntity = ProdutoRequestMapper.converter(mockProduto);

        assertNotNull(produtoEntity);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoEntity.getIdProduto()),
                () -> assertEquals("shampoo", produtoEntity.getNomeProduto()),
                () -> assertEquals("brilho intenso", produtoEntity.getDescricaoProduto()),
                () -> assertEquals("Kerastase", produtoEntity.getMarcaProduto()),
                () -> assertEquals(120.0, produtoEntity.getPrecoProduto()),
                () -> assertEquals("12/12/1990", produtoEntity.getDataCadastro()),
                () -> assertTrue(produtoEntity.getProdutoAtivo()),
                () -> assertFalse(produtoEntity.getProdutoOfertado()),
                () -> assertEquals(0, produtoEntity.getProdutoPorcentagemOferta())
        );
    }

    private Produto mockProduto(){
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(120.0)
                .dataCadastro("12/12/1990")
                .ofertado(false)
                .ativo(true)
                .porcentagemOferta(0)
                .build();
    }
}