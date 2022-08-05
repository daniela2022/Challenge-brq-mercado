package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;

public interface OfertaUseCase {

    void atualizarOferta(List<Produto> produtos);

    Produto detalharProdutoPorId(String idProduto);

    List<Produto> listarOferta();
}
