package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;

public interface AtivacaoGateway {

    void atualizarAtivacao(Produto produto);

    List<Produto> listarAtivacoes();

    void deletarAtivacao(Produto produto);
}
