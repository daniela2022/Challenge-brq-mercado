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
                () -> assertEquals(5,departamentoModelResponse.getIdDepartamento()),
                () -> assertEquals("Eletrônico",departamentoModelResponse.getNome()),
                () -> assertEquals("Produtos eletrônicos", departamentoModelResponse.getDescricao())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockDepartamento = mockDepartamentoResponse();

        List<DepartamentoModelResponse> departamentosModelResponse = DepartamentoEntryPointResponseMapper.convert(List.of(mockDepartamento));

        assertNotNull(departamentosModelResponse);
        assertAll(
                () -> assertEquals(5,departamentosModelResponse.get(0).getIdDepartamento()),
                () -> assertEquals("Eletrônico",departamentosModelResponse.get(0).getNome()),
                () -> assertEquals("Produtos eletrônicos", departamentosModelResponse.get(0).getDescricao())
        );
    }

    private Departamento mockDepartamentoResponse(){
        return Departamento.builder()
                .id(5)
                .nome("Eletrônico")
                .descricao("Produtos eletrônicos")
                .build();
    }

}