package com.challengebrq.mercado.projetochallenge.entrypoint.handler;

import lombok.Getter;

@Getter
public enum TipoProblema {

    DADO_INVALIDO("/dados-invalidos", "Dados inválidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido");

    private String title;
    private String uri;

    TipoProblema(String path, String title){
        this.uri = "https://mercado.com.br" + path;
        this.title = title;
    }
}
