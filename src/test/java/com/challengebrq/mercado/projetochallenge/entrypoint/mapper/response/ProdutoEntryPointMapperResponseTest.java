package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.ProdutoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoEntryPointMapperResponseTest {

    @Test
    void testeConverteResponseSucesso(){
        Produto mockProduto = mockProduto();

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProdutoParaModel(mockProduto);

        assertNotNull(produtoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtoModelResponse.getIdProduto()),
                () -> assertEquals("shampoo",produtoModelResponse.getNome()),
                () -> assertEquals("brilho intenso", produtoModelResponse.getDescricao()),
                () -> assertEquals("Kerastase", produtoModelResponse.getMarca()),
                () -> assertEquals(120.0, produtoModelResponse.getPreco()),
                () -> assertEquals("22/05/2018", produtoModelResponse.getDataCadastro()),
                () -> assertEquals("23/06/2019", produtoModelResponse.getDataAtualizacao()),
                () -> assertTrue(produtoModelResponse.getAtivo()),
                () -> assertFalse(produtoModelResponse.getOfertado()),
                () -> assertEquals(0, produtoModelResponse.getPorcentagemOferta()),
                () -> assertEquals(1, produtoModelResponse.getDepartamentos().get(0).getIdDepartamento())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockProduto = mockProduto();

        List<ProdutoModelResponse> produtoModelResponse = ProdutoEntryPointMapperResponse.convert(List.of(mockProduto));

        assertNotNull(produtoModelResponse);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108",produtoModelResponse.get(0).getIdProduto()),
                () -> assertEquals("shampoo",produtoModelResponse.get(0).getNome()),
                () -> assertEquals("Kerastase", produtoModelResponse.get(0).getMarca()),
                () -> assertEquals(120.0, produtoModelResponse.get(0).getPreco())
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
                .departamentos(List.of(mockDepartamento()))
                .build();
    }

    private Departamento mockDepartamento() {
        return Departamento.builder()
                .id(1)
                .build();
    }
}