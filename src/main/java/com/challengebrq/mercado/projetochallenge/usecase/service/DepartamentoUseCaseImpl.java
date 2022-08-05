package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.DuplicidadeNomeException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoUseCaseImpl implements DepartamentoUseCase {

    private final DepartamentoGateway departamentoGateway;

    public DepartamentoUseCaseImpl(DepartamentoGateway departamentoGateway) {
        this.departamentoGateway = departamentoGateway;
    }

    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        validarDuplicidadeNomeDepartamento(departamento);

        return departamentoGateway.criarDepartamento(departamento);
    }

    @Override
    public List<Departamento> listarDepartamento(String nome) {

        return departamentoGateway.listarDepartamento(nome);
    }

    @Override
    public void deletarDepartamento(Long idDepartamento) {
        var departamento = buscarDepartamentoPorId(idDepartamento);

        departamentoGateway.deletarDepartamentoPorId(departamento.getId());
    }

    @Override
    public Departamento buscarDepartamentoPorId(Long idDepartamento) {
        return departamentoGateway.buscarDepartamentoPorId(idDepartamento)
                .orElseThrow(() -> new EntidadeNaoEncontradaException
                        (String.format("Não existe cadastro de departamento com código %s", idDepartamento)));
    }

    public void validarDuplicidadeNomeDepartamento(Departamento departamentoRequest){
        departamentoGateway.buscarDepartamentoPorNome(departamentoRequest.getNome())
                .ifPresent(departamento -> {
                    throw new DuplicidadeNomeException((String.format("O nome do departamento '%s' não pode estar em duplicidade, o mesmo " +
                            "já tem cadastro no sistema", departamentoRequest.getNome())));
                });
    }

}
