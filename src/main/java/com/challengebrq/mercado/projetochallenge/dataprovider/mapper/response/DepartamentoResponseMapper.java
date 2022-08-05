package com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DepartamentoResponseMapper {

    public static Departamento convert (DepartamentoEntity departamentoEntity) {
        return Departamento.builder()
                .id(departamentoEntity.getIdDepartamento())
                .nome(departamentoEntity.getNome())
                .descricao(departamentoEntity.getDescricaoDepartamento())
                .build();
    }

    public static List<Departamento> convert(List<DepartamentoEntity> departamentos){
        List<Departamento> departamentosModelResponse = new ArrayList<>();

        departamentos.forEach(departamento -> {
            departamentosModelResponse.add(convert((departamento)));
        });
        return departamentosModelResponse;
    }
}
