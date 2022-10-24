package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class ProdutoPorcentagemNula extends RuntimeException {
    public ProdutoPorcentagemNula(String mensagem) {
        super(mensagem);
    }
}
