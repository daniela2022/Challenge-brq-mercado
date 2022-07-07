package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.ProdutoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntryPointMapperResponseTest {

    @Test
    void testeConverteResponseSucesso(){
        Produto mockProduto = mockProduto();

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converter(mockProduto);

        assertNotNull(produtoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtoModelResponse.getIdProduto()),
                () -> assertEquals("shampoo",produtoModelResponse.getNomeProduto()),
                () -> assertEquals("brilho intenso", produtoModelResponse.getDescricaoProduto()),
                () -> assertEquals("Kerastase", produtoModelResponse.getMarcaProduto()),
                () -> assertEquals(120.0, produtoModelResponse.getPrecoProduto()),
                () -> assertEquals("22/05/2018", produtoModelResponse.getDataCadastro()),
                () -> assertEquals("23/06/2019", produtoModelResponse.getDataAtualizacao()),
                () -> assertTrue(produtoModelResponse.getAtivoProduto()),
                () -> assertFalse(produtoModelResponse.getOfertadoProduto()),
                () -> assertEquals(0, produtoModelResponse.getPorcentagemOferta())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockProduto = mockProduto();

        List<ProdutoModelResponse> produtoModelResponse = ProdutoEntryPointMapperResponse.convert(List.of(mockProduto));

        assertNotNull(produtoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtoModelResponse.get(0).getIdProduto()),
                () -> assertEquals("shampoo",produtoModelResponse.get(0).getNomeProduto()),
                () -> assertEquals("brilho intenso", produtoModelResponse.get(0).getDescricaoProduto()),
                () -> assertEquals("Kerastase", produtoModelResponse.get(0).getMarcaProduto()),
                () -> assertEquals(120.0, produtoModelResponse.get(0).getPrecoProduto()),
                () -> assertEquals("22/05/2018", produtoModelResponse.get(0).getDataCadastro()),
                () -> assertEquals("23/06/2019", produtoModelResponse.get(0).getDataAtualizacao()),
                () -> assertTrue(produtoModelResponse.get(0).getAtivoProduto()),
                () -> assertFalse(produtoModelResponse.get(0).getOfertadoProduto()),
                () -> assertEquals(0, produtoModelResponse.get(0).getPorcentagemOferta())
        );
    }

    private Produto mockProduto(){
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(120.0)
                .dataCadastro("22/05/2018")
                .dataAtualizacao("23/06/2019")
                .ativo(true)
                .ofertado(false)
                .porcentagemOferta(0)
                .build();
    }

}