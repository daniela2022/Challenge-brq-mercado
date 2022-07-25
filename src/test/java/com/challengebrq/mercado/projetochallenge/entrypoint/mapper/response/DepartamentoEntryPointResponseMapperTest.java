package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.DepartamentoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoEntryPointResponseMapperTest {

    @Test
    void testeConverteResponseSucesso(){
        Departamento mockDepartamento = mockDepartamentoResponse();

        DepartamentoModelResponse departamentoModelResponse = DepartamentoEntryPointResponseMapper.converterDepartamentoParaModel(mockDepartamento);

        assertNotNull(departamentoModelResponse);
        assertAll(
                () -> assertEquals(5L,departamentoModelResponse.getIdDepartamento()),
                () -> assertEquals("Eletrônico",departamentoModelResponse.getNomeDepartamento()),
                () -> assertEquals("Produtos eletrônicos", departamentoModelResponse.getDescricaoDepartamento())
        );
    }

    private Departamento mockDepartamentoResponse(){
        return Departamento.builder()
                .id(5L)
                .nome("Eletrônico")
                .descricao("Produtos eletrônicos")
                .build();
    }

}