package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;

import java.util.List;

public interface DepartamentoRepositoryQuery {

    List<DepartamentoEntity> findListar(String nome);
}
