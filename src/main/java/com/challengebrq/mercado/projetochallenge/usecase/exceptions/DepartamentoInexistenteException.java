package com.challengebrq.mercado.projetochallenge.usecase.exceptions;

public class DepartamentoInexistenteException extends RuntimeException {
    public DepartamentoInexistenteException(String mensagem) {
        super(mensagem);
    }
}
