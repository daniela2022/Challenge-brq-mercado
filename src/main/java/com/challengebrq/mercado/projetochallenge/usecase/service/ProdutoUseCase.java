package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);

    List<Produto> listarProduto(Produto produto);

    Produto detalharProdutoPorId(String idProduto);

    void deletarProduto(String idProduto);
}
