package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelFiltroRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntryPointMapperRequestTest {

    @Test
    void testeConverteRequestSucesso(){
        ProdutoModelRequest mockProduto = mockProdutoModelRequest();

        Produto produto = ProdutoEntryPointMapperRequest.converter(mockProduto);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("shampoo",produto.getNome()),
                () -> assertEquals("brilho intenso", produto.getDescricao()),
                () -> assertEquals("Kerastase", produto.getMarca()),
                () -> assertEquals(120.0, produto.getPreco())
        );
    }

    @Test
    void testeConverteModelFilterRequestSucesso(){
        var mockProduto = mockProdutoModelFiltroRequest();

        Produto produto = ProdutoEntryPointMapperRequest.convert(mockProduto);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId()),
                () -> assertEquals("shampoo",produto.getNome()),
                () -> assertEquals("Kerastase", produto.getMarca()),
                () -> assertEquals(125.0, produto.getPreco())
        );
    }

    @Test
    void testeConverteStringIdProdutoSucesso(){
        var mockProduto = mockProdutoStringIdProduto("3322c422-a336-4064-96b3-2fc39ea4a108");

        Produto produto = ProdutoEntryPointMapperRequest.convert(mockProduto.getId());

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId())
        );
    }

    private Produto mockProdutoStringIdProduto(String idProduto){
        return Produto.builder()
                .id(idProduto)
                .build();
    }







    private ProdutoModelFiltroRequest mockProdutoModelFiltroRequest(){
        return ProdutoModelFiltroRequest.builder()
                .idProduto("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nomeProduto("shampoo")
                .marcaProduto("Kerastase")
                .precoProduto(125.0)
                .build();
    }

    private ProdutoModelRequest mockProdutoModelRequest(){
        return ProdutoModelRequest.builder()
                .nomeProduto("shampoo")
                .descricaoProduto("brilho intenso")
                .marcaProduto("Kerastase")
                .precoProduto(120.0)
                .build();
    }
}