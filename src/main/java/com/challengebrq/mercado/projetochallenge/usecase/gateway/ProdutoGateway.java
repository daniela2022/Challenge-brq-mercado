package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Optional<Produto> buscarProdutoPorNome(String nomeProduto);

    Produto criarProduto(Produto produto);

    List<Produto> listarProdutos(Produto produto);

    Optional<Produto> detalharProdutoPorId(String id);

   void deletarProdutoPorId(String idProduto);
}
