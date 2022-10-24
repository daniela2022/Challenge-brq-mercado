package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.DepartamentoModelRequest;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DepartamentoEntryPointMapperRequest {

    public static Departamento converter(DepartamentoModelRequest departamentoModelRequest) {
        return Departamento.builder()
                .nome(departamentoModelRequest.getNome())
                .descricao(departamentoModelRequest.getDescricao())
                .build();
    }
}
