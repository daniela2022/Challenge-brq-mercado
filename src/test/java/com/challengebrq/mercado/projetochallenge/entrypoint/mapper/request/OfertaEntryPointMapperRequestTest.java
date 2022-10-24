package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequestRemover;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfertaEntryPointMapperRequestTest {

    @Test
    void testeConverteRequestSucesso(){
        OfertaModelRequest mockProduto = mockProdutoModelRequest();

        Produto produto = OfertaEntryPointMapperRequest.converter(mockProduto);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1",produto.getId()),
                () -> assertEquals(10, produto.getPorcentagemOferta())
        );
    }

//    @Test
//    void testeConverteListaRequestSucesso(){
//        var mockOfertaModelRequest = mockProdutoModelRequest();
//
//        List<Produto> produtos = OfertaEntryPointMapperRequest.convert(List.of(mockOfertaModelRequest));
//
//        assertNotNull(produtos);
//        assertAll(
//                () -> assertEquals("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1",produtos.get(0).getId()),
//                () -> assertEquals(10, produtos.get(0).getPorcentagemOferta())
//        );
//    }

    @Test
    void testeConverteRequestDeleteSucesso(){
        OfertaModelRequestRemover mockProduto = mockProdutoModelRequestRemover();

        Produto produto = OfertaEntryPointMapperRequest.converterDelete(mockProduto);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1",produto.getId())
        );
    }

    @Test
    void testeConverteListaRequestDeleteSucesso(){
        var mockOfertaModelRequestRemover = mockProdutoModelRequestRemover();

        List<Produto> produtos = OfertaEntryPointMapperRequest.convertLista(List.of(mockOfertaModelRequestRemover));

        assertNotNull(produtos);
        assertAll(
                () -> assertEquals("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1",produtos.get(0).getId())
        );
    }

    private OfertaModelRequest mockProdutoModelRequest(){
        return OfertaModelRequest.builder()
                .id("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1")
                .porcentagemOferta(10)
                .build();
    }

    private OfertaModelRequestRemover mockProdutoModelRequestRemover(){
        return OfertaModelRequestRemover.builder()
                .id("f0ff7bcc-be58-4ea7-82a2-eb557ebb76a1")
                .build();
    }

}