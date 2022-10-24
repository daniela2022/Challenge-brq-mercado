package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoUseCase {

    Produto criarProduto(Produto produto);

    List<Produto> listarProduto(String nome, String marca,Double preco, Integer departamento, Boolean ativo);

    Produto detalharProdutoPorId(String idProduto);

    void deletarProduto(String idProduto);

    Produto atualizarParcialProduto(Produto produto);
}
