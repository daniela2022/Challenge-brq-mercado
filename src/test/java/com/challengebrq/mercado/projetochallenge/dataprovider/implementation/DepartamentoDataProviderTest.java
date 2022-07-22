package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.DepartamentoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class DepartamentoDataProviderTest {

    @InjectMocks
    private DepartamentoDataProvider departamentoDataProvider;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @Test
    void testeCadastrarDepartamentoSucesso() {
        DepartamentoEntity departamentoResponse = mockDepartamentoEntity();

        given(departamentoRepository.save(Mockito.any())).willReturn(departamentoResponse);

        Departamento departamentoDomain = departamentoDataProvider.criarDepartamento(new Departamento());

        assertNotNull(departamentoDomain);
        assertAll(
                () -> assertEquals(1L, departamentoDomain.getId()),
                () -> assertEquals("Telefone", departamentoDomain.getNome()),
                () -> assertEquals("Celular", departamentoDomain.getDescricao())
        );
    }

    @Test
    void testeCadastrarDepartamentoFalha() {

        given(departamentoRepository.save(Mockito.any())).willThrow(new RuntimeException((" Erro de comunicação com o banco.")));

        CadastroException departamentoException = assertThrows(CadastroException.class,
                () -> departamentoDataProvider.criarDepartamento(new Departamento()));

        assertEquals("Erro ao realizar o cadastro do departamento", departamentoException.getMessage());
    }

    @Test
    void testeBuscarDepartamentoPorNomeSucesso() {
        DepartamentoEntity departamentoRequest = mockDepartamentoEntity();

        given(departamentoRepository.findByNomeDepartamento(departamentoRequest.getNomeDepartamento()))
                .willReturn(Optional.of(departamentoRequest));

        Optional<Departamento> departamento = departamentoDataProvider.buscarDepartamentoPorNome(departamentoRequest.getNomeDepartamento());

        assertTrue(departamento.isPresent());
        assertAll(
                () -> assertEquals(1L, departamento.get().getId()),
                () -> assertEquals("Telefone", departamento.get().getNome()),
                () -> assertEquals("Celular", departamento.get().getDescricao())
                );
    }

    private DepartamentoEntity mockDepartamentoEntity() {
        return DepartamentoEntity.builder()
                .idDepartamento(1L)
                .nomeDepartamento("Telefone")
                .descricaoDepartamento("Celular")
                .build();
    }
}