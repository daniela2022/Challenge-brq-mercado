package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class DepartamentoUseCaseImplTest {

    @InjectMocks
    private DepartamentoUseCaseImpl departamentoUseCase;

    @Mock
    private DepartamentoGateway departamentoGateway;

    @Test
    void testeCriarDepartamentoSucesso() {
        Departamento departamentoParaSerCriado = mockDepartamentoRequest();
        Departamento departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.buscarDepartamentoPorNome(departamentoParaSerCriado.getNome())).willReturn(Optional.empty());
        given(departamentoGateway.criarDepartamento(departamentoParaSerCriado)).willReturn(departamentoCriado);

        Departamento departamento = departamentoUseCase.criarDepartamento(departamentoParaSerCriado);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals(1L, departamento.getId()),
                () -> assertEquals("Telefone", departamento.getNome()),
                () -> assertEquals("Celular", departamento.getDescricao())
        );
    }

    private Departamento mockDepartamentoRequest() {
        return Departamento.builder()
                .nome("Telefone")
                .descricao("Celular")
                .build();
    }

    private Departamento mockDepartamentoResponse() {
        return Departamento.builder()
                .id(1L)
                .nome("Telefone")
                .descricao("Celular")
                .build();

    }

}