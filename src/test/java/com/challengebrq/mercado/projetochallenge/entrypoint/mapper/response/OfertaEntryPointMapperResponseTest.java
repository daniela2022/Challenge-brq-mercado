package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.OfertaModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfertaEntryPointMapperResponseTest {

    @Test
    void testeConverteResponseSucesso(){
        Produto mockProduto = mockProduto();

        OfertaModelResponse ofertaModelResponse = OfertaEntryPointMapperResponse.converterOfertaParaModel(mockProduto);

        assertNotNull(ofertaModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",ofertaModelResponse.getIdProduto()),
                () -> assertEquals("shampoo",ofertaModelResponse.getNome()),
                () -> assertEquals("Kerastase", ofertaModelResponse.getMarca()),
                () -> assertEquals(120.0, ofertaModelResponse.getPreco()),
                () -> assertFalse(ofertaModelResponse.getOfertado()),
                () -> assertEquals(10, ofertaModelResponse.getPorcentagemOferta())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockProduto = mockProduto();

        List<OfertaModelResponse> ofertaModelResponse = OfertaEntryPointMapperResponse.convert(List.of(mockProduto));

        assertNotNull(ofertaModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",ofertaModelResponse.get(0).getIdProduto()),
                () -> assertEquals("shampoo",ofertaModelResponse.get(0).getNome()),
                () -> assertEquals("Kerastase", ofertaModelResponse.get(0).getMarca()),
                () -> assertEquals(120.0, ofertaModelResponse.get(0).getPreco()),
                () -> assertFalse(ofertaModelResponse.get(0).getOfertado()),
                () -> assertEquals(10, ofertaModelResponse.get(0).getPorcentagemOferta())
        );
    }

    private Produto mockProduto(){
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .marca("Kerastase")
                .preco(120.0)
                .ofertado(false)
                .porcentagemOferta(10)
                .build();
    }

}