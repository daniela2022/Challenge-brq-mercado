package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class DuplicidadeNomeException extends RuntimeException {
    public DuplicidadeNomeException(String mensagem) {
        super(mensagem);
    }
}
