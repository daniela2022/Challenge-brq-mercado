package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.Map;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);

    List<Produto> listarProduto();

    Produto detalharProdutoPorId(String idProduto);

    void deletarProduto(String idProduto);

    Produto atualizarParcialProduto(Produto produto);
}
