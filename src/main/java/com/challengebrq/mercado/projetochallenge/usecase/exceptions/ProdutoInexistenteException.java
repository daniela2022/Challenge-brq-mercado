package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class ProdutoInexistenteException extends RuntimeException {
    public ProdutoInexistenteException(String mensagem) {
        super(mensagem);
    }
}
