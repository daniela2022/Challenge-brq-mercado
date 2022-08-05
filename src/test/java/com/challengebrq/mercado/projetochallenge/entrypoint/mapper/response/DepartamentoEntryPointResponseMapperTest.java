package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.DepartamentoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void testeConverteListaResponseSucesso(){
        var mockDepartamento = mockDepartamentoResponse();

        List<DepartamentoModelResponse> departamentosModelResponse = DepartamentoEntryPointResponseMapper.convert(List.of(mockDepartamento));

        assertNotNull(departamentosModelResponse);
        assertAll(
                () -> assertEquals(5L,departamentosModelResponse.get(0).getIdDepartamento()),
                () -> assertEquals("Eletrônico",departamentosModelResponse.get(0).getNomeDepartamento()),
                () -> assertEquals("Produtos eletrônicos", departamentosModelResponse.get(0).getDescricaoDepartamento())
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