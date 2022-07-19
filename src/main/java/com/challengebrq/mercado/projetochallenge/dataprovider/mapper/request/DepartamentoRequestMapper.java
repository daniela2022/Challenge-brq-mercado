package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

public class DepartamentoRequestMapper {

    public static DepartamentoEntity convert (Departamento departamento) {
        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getId())
                .nomeDepartamento(departamento.getNome())
                .descricaoDepartamento(departamento.getDescricao())
                .build();
    }
}
