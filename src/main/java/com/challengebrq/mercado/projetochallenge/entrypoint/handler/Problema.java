package com.challengebrq.mercado.projetochallenge.entrypoint.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime timestamp;
    private List<Field> fields;

    private String userMessage;

    @Getter
    @Builder
    public static class Field {
        private String name;
        private String userMessage;
    }
}
