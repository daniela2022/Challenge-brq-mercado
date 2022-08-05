package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoGateway {

    Departamento criarDepartamento(Departamento departamento);

    Optional<Departamento> buscarDepartamentoPorNome(String nomeDepartamento);

    List<Departamento> listarDepartamento(String nome);

    Optional<Departamento> buscarDepartamentoPorId(Long id);

    void deletarDepartamentoPorId(Long idDepartamento);
}
