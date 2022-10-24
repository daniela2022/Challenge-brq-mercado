package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class ProdutoInativoException extends RuntimeException {
    public ProdutoInativoException(String mensagem) {
        super(mensagem);
    }
}
