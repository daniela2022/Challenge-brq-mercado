package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoResponseMapperTest {

    @Test
    void testeConverteResponseSucesso(){
        ProdutoEntity mockProdutoEntity = mockProdutoEntity();

        Produto produto = ProdutoResponseMapper.converter(mockProdutoEntity);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produto.getId()),
                () -> assertEquals("shampoo",produto.getNome()),
                () -> assertEquals("brilho intenso", produto.getDescricao()),
                () -> assertEquals("Kerastase", produto.getMarca()),
                () -> assertEquals(120.0, produto.getPreco()),
                () -> assertEquals("04/08/2005", produto.getDataCadastro()),
                () -> assertEquals("04/08/2006", produto.getDataAtualizacao()),
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

    private ProdutoEntity mockProdutoEntity(){
        return ProdutoEntity.builder()
                .idProduto("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nomeProduto("shampoo")
                .descricaoProduto("brilho intenso")
                .marcaProduto("Kerastase")
                .precoProduto(120.0)
                .dataCadastro("04/08/2005")
                .dataAtualizacao("04/08/2006")
                .produtoOfertado(false)
                .produtoAtivo(true)
                .produtoPorcentagemOferta(0)
                .build();
    }
}