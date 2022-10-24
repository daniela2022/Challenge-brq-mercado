package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

import java.util.List;
import java.util.stream.Collectors;

public class DepartamentoRequestMapper {

    public static DepartamentoEntity convert (Departamento departamento) {
        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getId())
                .nome(departamento.getNome())
                .descricaoDepartamento(departamento.getDescricao())
                .build();
    }

    public static DepartamentoEntity convertId(Departamento departamento){
        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getId())
                .build();
    }

    public static List<DepartamentoEntity> convertList(List<Departamento> departamentos){
        return departamentos.stream()
                .map(DepartamentoRequestMapper::convertId)
                .collect(Collectors.toList());
    }


    public static DepartamentoEntity convertIdNome(Departamento departamento){
        return DepartamentoEntity.builder()
                .idDepartamento(departamento.getId())
                .nome(departamento.getNome())
                .build();

    }

    public static List<DepartamentoEntity> convertLista(List<Departamento> departamentos){
        return departamentos.stream()
                .map(DepartamentoRequestMapper::convertIdNome)
                .collect(Collectors.toList());
    }

}
