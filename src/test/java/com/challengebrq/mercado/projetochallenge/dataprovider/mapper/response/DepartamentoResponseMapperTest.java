package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoResponseMapperTest {

    @Test
    void testeConverteResponseSucesso(){
        DepartamentoEntity mockProdutoEntity = mockDepartamentoEntity();

        Departamento departamento = DepartamentoResponseMapper.convert(mockProdutoEntity);

        assertNotNull(departamento);
        assertAll(
                () -> assertEquals(2L,departamento.getId()),
                () -> assertEquals("shampoo",departamento.getNome()),
                () -> assertEquals("shampoo para cabelos coloridos", departamento.getDescricao())
        );
    }

    @Test
    void testeConverteListaResponseSucesso(){
        var mockDepartamento = mockDepartamentoEntity();

        List<Departamento> departamentos = DepartamentoResponseMapper.convert(List.of(mockDepartamentoEntity()));

        assertNotNull(departamentos);
        assertAll(
                () -> assertEquals(2L,departamentos.get(0).getId()),
                () -> assertEquals("shampoo",departamentos.get(0).getNome()),
                () -> assertEquals("shampoo para cabelos coloridos", departamentos.get(0).getDescricao())
        );
    }

    private DepartamentoEntity mockDepartamentoEntity(){
        return DepartamentoEntity.builder()
                .idDepartamento(2L)
                .nome("shampoo")
                .descricaoDepartamento("shampoo para cabelos coloridos")
                .build();
    }

}