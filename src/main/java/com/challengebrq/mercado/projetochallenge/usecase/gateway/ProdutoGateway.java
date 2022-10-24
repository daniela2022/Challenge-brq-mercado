package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {

    Optional<Produto> buscarProdutoPorNome(String nomeProduto);

    Produto criarProduto(Produto produto);

    List<Produto> listarProdutos(String nome, String marca,Double preco, Integer departamento, Boolean ativo);

    Optional<Produto> detalharProdutoPorId(String id);

   void deletarProdutoPorId(String idProduto);

   Produto atualizarParcialProduto(Produto produto);
}
