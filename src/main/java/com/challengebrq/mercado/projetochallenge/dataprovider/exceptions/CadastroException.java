package com.challengebrq.mercado.projetochallenge.dataprovider.exceptions;

public class CadastroException extends RuntimeException {
    public CadastroException(String mensagem, Exception exception) {
        super(mensagem,exception);
    }
}
