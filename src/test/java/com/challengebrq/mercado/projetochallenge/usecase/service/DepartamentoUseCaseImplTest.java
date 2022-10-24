package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
                () -> assertEquals(1, departamento.getId()),
                () -> assertEquals("Telefone", departamento.getNome()),
                () -> assertEquals("Celular", departamento.getDescricao())
        );
    }

    @Test
    void testeListarDepartamentoComNome() {
        var departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.listarDepartamento("Telefone")).willReturn(List.of(departamentoCriado));

        List<Departamento> departamentos = departamentoUseCase.listarDepartamento("Telefone");

        assertNotNull(departamentos);
        assertAll(
                () -> assertEquals(1, departamentos.get(0).getId()),
                () -> assertEquals("Telefone", departamentos.get(0).getNome()),
                () -> assertEquals("Celular", departamentos.get(0).getDescricao())
        );
    }

    @Test
    void testeListarDepartamentoComNomeVazio() {
        var departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.listarDepartamento(null)).willReturn(List.of(departamentoCriado));

        List<Departamento> departamentos = departamentoUseCase.listarDepartamento(null);

        assertNotNull(departamentos);
        assertAll(
                () -> assertEquals(1, departamentos.get(0).getId()),
                () -> assertEquals("Telefone", departamentos.get(0).getNome()),
                () -> assertEquals("Celular", departamentos.get(0).getDescricao())
        );
    }

    @Test
    void testeBuscarDepartamentoPorId() {

        var departamentoCriado = mockDepartamentoResponse();

        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(departamentoCriado));

        Departamento departamento = departamentoUseCase.buscarDepartamentoPorId(1);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals(1, departamento.getId())
        );
    }

    @Test
    void testeBuscarDepartamentoPorIdNulo() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> departamentoUseCase.buscarDepartamentoPorId(null));
    }

    @Test
    void testeDeletarDepartamentoPorId() {
        var departamentoCriado = mockDepartamentoResponse();
        when(departamentoGateway.buscarDepartamentoPorId(departamentoCriado.getId())).thenReturn(Optional.of(departamentoCriado));

        departamentoUseCase.deletarDepartamento(departamentoCriado.getId());

        verify(departamentoGateway, times(1)).deletarDepartamentoPorId(departamentoCriado.getId());
    }

    private Departamento mockDepartamentoRequest() {
        return Departamento.builder()
                .nome("Telefone")
                .descricao("Celular")
                .build();
    }

    private Departamento mockDepartamentoResponse() {
        return Departamento.builder()
                .id(1)
                .nome("Telefone")
                .descricao("Celular")
                .build();

    }

}