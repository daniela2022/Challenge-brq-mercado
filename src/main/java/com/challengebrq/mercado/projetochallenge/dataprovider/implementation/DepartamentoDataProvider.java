package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request.DepartamentoRequestMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response.DepartamentoResponseMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.DepartamentoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DepartamentoDataProvider implements DepartamentoGateway {

    private final DepartamentoRepository departamentoRepository;

    @Override
    public Departamento criarDepartamento(Departamento departamento) {
        try {
            DepartamentoEntity departamentoAtual = DepartamentoRequestMapper.convert(departamento);
            DepartamentoEntity departamentoCadastrado = departamentoRepository.save(departamentoAtual);

            return DepartamentoResponseMapper.convert(departamentoCadastrado);
        }catch (Exception exception){
            throw new CadastroException("Erro ao realizar o cadastro do departamento", exception);
        }
    }

    @Override
    public Optional<Departamento> buscarDepartamentoPorNome(String nomeDepartamento) {
        return departamentoRepository.findByNome(nomeDepartamento).map(DepartamentoResponseMapper::convert);
    }

    @Override
    public List<Departamento> listarDepartamento(String nome) {
        List<DepartamentoEntity> departamentos = departamentoRepository.findListar(nome);

        return DepartamentoResponseMapper.convert(departamentos);
    }

    @Override
    public Optional<Departamento> buscarDepartamentoPorId(Integer id) {
        return departamentoRepository.findById(id)
                .map(DepartamentoResponseMapper::convert);
    }

    @Override
    public void deletarDepartamentoPorId(Integer idDepartamento) {

        departamentoRepository.deleteById(idDepartamento);
    }
}
