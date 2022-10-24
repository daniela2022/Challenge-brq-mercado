package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import  com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.DepartamentoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
                () -> assertEquals(1, departamentoDomain.getId()),
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

        given(departamentoRepository.findByNome(departamentoRequest.getNome()))
                .willReturn(Optional.of(departamentoRequest));

        Optional<Departamento> departamento = departamentoDataProvider.buscarDepartamentoPorNome(departamentoRequest.getNome());

        assertTrue(departamento.isPresent());
        assertAll(
                () -> assertEquals(1, departamento.get().getId()),
                () -> assertEquals("Telefone", departamento.get().getNome()),
                () -> assertEquals("Celular", departamento.get().getDescricao())
        );
    }

    @Test
    void testeListarDepartamentosSucessoComNome() {
        var departamentoResponse =  List.of(mockDepartamentoEntity());

        given(departamentoRepository.findListar("Telefone")).willReturn(departamentoResponse);
        given(departamentoRepository.findAll()).willReturn(departamentoResponse);

        List<Departamento> departamentoDomain = departamentoDataProvider.listarDepartamento("Telefone");

        assertNotNull(departamentoDomain);
        assertAll(
                () -> assertEquals(1, departamentoDomain.get(0).getId()),
                () -> assertEquals("Telefone", departamentoDomain.get(0).getNome()),
                () -> assertEquals("Celular", departamentoDomain.get(0).getDescricao())
        );
    }

    @Test
    void testeListarDepartamentosSucessoComNomeVazio() {
        var departamentoResponse =  List.of(mockDepartamentoEntity());

        given(departamentoRepository.findListar(null)).willReturn(departamentoResponse);
        given(departamentoRepository.findAll()).willReturn(departamentoResponse);

        List<Departamento> departamentoDomain = departamentoDataProvider.listarDepartamento(null);

        assertNotNull(departamentoDomain);
        assertAll(
                () -> assertEquals(1, departamentoDomain.get(0).getId()),
                () -> assertEquals("Telefone", departamentoDomain.get(0).getNome()),
                () -> assertEquals("Celular", departamentoDomain.get(0).getDescricao())
        );
    }

    @Test
    void testebuscarDepartamentoPorIdSucesso() {
        Departamento departamentoRequest = mockDepartamento();
        DepartamentoEntity departamentoResponse = mockDepartamentoEntity();

        given(departamentoRepository.findById(departamentoRequest.getId()))
                .willReturn(Optional.of(departamentoResponse));

        Optional<Departamento> departamento = departamentoDataProvider.buscarDepartamentoPorId(departamentoRequest.getId());

        assertTrue(departamento.isPresent());
        assertAll(
                () -> assertEquals(1, departamento.get().getId())
        );
    }

    @Test
    void testeDeletarDepartamentoPorId(){
        var departamentoRequest = mockDepartamento();
        departamentoDataProvider.deletarDepartamentoPorId(departamentoRequest.getId());

        verify(departamentoRepository, times(1)).deleteById(departamentoRequest.getId());
    }

    private DepartamentoEntity mockDepartamentoEntity() {
        return DepartamentoEntity.builder()
                .idDepartamento(1)
                .nome("Telefone")
                .descricaoDepartamento("Celular")
                .build();
    }

    private Departamento mockDepartamento() {
        return Departamento.builder()
                .id(1)
                .nome("shampoo")
                .descricao("shampoo para cabelos rebeldes")
                .build();
    }

}