package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartamentoRequestMapperTest {

    @Test
    void testeConverteRequestSucesso(){
        Departamento mockDepartamento = mockDepartamento();

        DepartamentoEntity departamentoEntity = DepartamentoRequestMapper.convert(mockDepartamento);

        assertNotNull(departamentoEntity);
        assertAll(
                () -> assertEquals(2, departamentoEntity.getIdDepartamento()),
                () -> assertEquals("shampoo", departamentoEntity.getNome()),
                () -> assertEquals("shampoo para cabelos coloridos", departamentoEntity.getDescricaoDepartamento())
        );
    }

    private Departamento mockDepartamento(){
        return Departamento.builder()
                .id(2)
                .nome("shampoo")
                .descricao("shampoo para cabelos coloridos")
                .build();
    }

}