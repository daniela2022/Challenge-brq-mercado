package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfertaResponseMapperTest {

    @Test
    void testeConverteResponseSucesso(){
        ProdutoEntity mockProdutoEntity = mockProdutoEntity();

        Produto produto = OfertaResponseMapper.converter(mockProdutoEntity);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produto.getId()),
                () -> assertEquals("shampoo",produto.getNome()),
                () -> assertEquals("Kerastase", produto.getMarca()),
                () -> assertEquals(120.0, produto.getPreco()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockProduto = mockProdutoEntity();

        List<Produto> produtos = OfertaResponseMapper.convert(List.of(mockProduto));

        assertNotNull(produtos);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtos.get(0).getId()),
                () -> assertEquals("shampoo",produtos.get(0).getNome()),
                () -> assertEquals("Kerastase", produtos.get(0).getMarca()),
                () -> assertEquals(120.0, produtos.get(0).getPreco()),
                () -> assertFalse(produtos.get(0).getOfertado()),
                () -> assertEquals(0, produtos.get(0).getPorcentagemOferta())
        );
    }

    private ProdutoEntity mockProdutoEntity(){
        return ProdutoEntity.builder()
                .idProduto("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nomeProduto("shampoo")
                .marcaProduto("Kerastase")
                .precoProduto(120.0)
                .produtoOfertado(false)
                .produtoPorcentagemOferta(0)
                .build();
    }
}