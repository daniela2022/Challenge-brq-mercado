package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class ProdutoOfertadoMenorZero extends RuntimeException {
    public ProdutoOfertadoMenorZero(String mensagem) {
        super(mensagem);
    }
}
