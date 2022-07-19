package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

import java.util.Optional;

public interface DepartamentoGateway {

    Departamento criarDepartamento(Departamento departamento);

    Optional<Departamento> buscarDepartamentoPorNome(String nomeDepartamento);
}
