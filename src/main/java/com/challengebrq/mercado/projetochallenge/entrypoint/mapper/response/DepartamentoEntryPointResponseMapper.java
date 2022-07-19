package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.DepartamentoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DepartamentoEntryPointResponseMapper {

    public static DepartamentoModelResponse converterDepartamentoParaModel(Departamento departamento){
        return DepartamentoModelResponse.builder()
                .idDepartamento(departamento.getId())
                .nomeDepartamento(departamento.getNome())
                .descricaoDepartamento(departamento.getDescricao())
                .build();
    }
}
