package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

public class DepartamentoResponseMapper {

    public static Departamento convert (DepartamentoEntity departamentoEntity) {
        return Departamento.builder()
                .id(departamentoEntity.getIdDepartamento())
                .nome(departamentoEntity.getNomeDepartamento())
                .descricao(departamentoEntity.getDescricaoDepartamento())
                .build();
    }
}
