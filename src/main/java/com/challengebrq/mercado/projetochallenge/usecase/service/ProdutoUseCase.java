package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);

    List<Produto> listarProduto();

    Produto detalharProdutoPorId(String idProduto);

    void deletarProduto(String idProduto);
}
