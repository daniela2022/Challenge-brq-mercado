package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.DepartamentoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequestAtualizar;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                () -> assertEquals(120.0, produto.getPreco()),
                () -> assertEquals(1, produto.getDepartamentos().get(0).getId())
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

    @Test
    void testeConvertAtualizarProdutoSucesso(){

        Produto produto = ProdutoEntryPointMapperRequest.convert("123456-D5", mockProdutoModelRequestAtualizar("123456-D5"));

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("123456-D5",produto.getId()),
                () -> assertEquals("shampoo",produto.getNome()),
                () -> assertEquals("brilho radiante", produto.getDescricao()),
                () -> assertEquals("Elseve", produto.getMarca()),
                () -> assertEquals(10.0, produto.getPreco())
        );
    }

    private Produto mockProdutoStringIdProduto(String idProduto){
        return Produto.builder()
                .id(idProduto)
                .build();
    }

    private ProdutoModelRequest mockProdutoModelRequest(){
        return ProdutoModelRequest.builder()
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(120.0)
                .codigosDepartamento(List.of(1))
                .build();
    }

    private ProdutoModelRequestAtualizar mockProdutoModelRequestAtualizar(String idProduto){
        return ProdutoModelRequestAtualizar.builder()
                .nome("shampoo")
                .descricao("brilho radiante")
                .marca("Elseve")
                .preco(10.0)
                .build();
    }

    private DepartamentoModelRequest mockDepartamento() {
        return DepartamentoModelRequest.builder()
                .nome("Eletronico")
                .build();
    }
}