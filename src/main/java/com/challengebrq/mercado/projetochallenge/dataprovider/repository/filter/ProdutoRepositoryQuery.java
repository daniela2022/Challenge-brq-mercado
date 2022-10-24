package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;

import java.util.List;

public interface ProdutoRepositoryQuery {

    List<ProdutoEntity> consultar(String nome, String marca,Double preco, Integer departamento, Boolean ativo);
}
