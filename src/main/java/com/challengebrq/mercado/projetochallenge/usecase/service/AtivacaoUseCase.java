package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;

public interface AtivacaoUseCase {

    void atualizarAtivacao(List<Produto> produtos);

    Produto detalharProdutoPorId(String idProduto);

    List<Produto> listarAtivacao();

    void deletarAtivacao(List<Produto> produtos);
}
