package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;

import java.util.List;

public interface DepartamentoUseCase {

    Departamento criarDepartamento(Departamento departamento);

    List<Departamento> listarDepartamento(String nome);

    void deletarDepartamento(Long idDepartamento);

    Departamento buscarDepartamentoPorId(Long idDepartamento);

}
