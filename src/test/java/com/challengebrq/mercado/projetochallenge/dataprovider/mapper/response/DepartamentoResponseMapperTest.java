package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import org.junit.jupiter.api.Test;

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

    private DepartamentoEntity mockDepartamentoEntity(){
        return DepartamentoEntity.builder()
                .idDepartamento(2L)
                .nomeDepartamento("shampoo")
                .descricaoDepartamento("shampoo para cabelos coloridos")
                .build();
    }

}