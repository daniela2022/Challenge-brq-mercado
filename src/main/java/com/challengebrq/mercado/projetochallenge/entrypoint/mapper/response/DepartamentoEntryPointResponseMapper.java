package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.DepartamentoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class DepartamentoEntryPointResponseMapper {

    public static DepartamentoModelResponse converterDepartamentoParaModel(Departamento departamento){
        return DepartamentoModelResponse.builder()
                .idDepartamento(departamento.getId())
                .nomeDepartamento(departamento.getNome())
                .descricaoDepartamento(departamento.getDescricao())
                .build();
    }

    public static List<DepartamentoModelResponse> convert(List<Departamento> departamentos){
        List<DepartamentoModelResponse> departamentoModelResponse = new ArrayList<>();

        departamentos.forEach(departamento -> {
            departamentoModelResponse.add(converterDepartamentoParaModel((departamento)));
        });
        return departamentoModelResponse;
    }

}
