package com.challengebrq.mercado.projetochallenge.usecase.gateway;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;

import java.util.List;

public interface OfertaGateway {

    void atualizarOferta(Produto produto);

    List<Produto> listarOfertas();

    void deletarOferta(Produto produto);
}
