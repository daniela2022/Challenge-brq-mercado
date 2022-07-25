package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.DepartamentoModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoEntryPointMapperRequestTest {

    @Test
    void testeConverteRequestSucesso(){
        DepartamentoModelRequest mockDepartamento = mockDepartamentoRequest();

        Departamento departamento = DepartamentoEntryPointMapperRequest.converter(mockDepartamento);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals("Telefone",departamento.getNome()),
                () -> assertEquals("Celular", departamento.getDescricao())
        );
    }

    private DepartamentoModelRequest mockDepartamentoRequest(){
        return DepartamentoModelRequest.builder()
                .nomeDepartamento("Telefone")
                .descricaoDepartamento("Celular")
                .build();
    }

}