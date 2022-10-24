package com.challengebrq.mercado.projetochallenge.entrypoint.handler;

import com.challengebrq.mercado.projetochallenge.usecase.exceptions.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MercadoExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato "
            + "com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
        }else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
        }

        TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
        String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

        Problema problem = createProblemBuilder(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        // Criei o método joinPath para reaproveitar em todos os métodos que precisam
        // concatenar os nomes das propriedades (separando por ".")
        String path = joinPath(ex.getPath());

        TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);

        Problema problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request){

        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', " +
                        "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());

        Problema problem = createProblemBuilder(status, problemType, detail)
                .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        status = HttpStatus.UNPROCESSABLE_ENTITY;
        TipoProblema tipoProblema =TipoProblema.ERRO_NEGOCIO;
        String detail = String.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e " +
                "tente novamente");

        BindingResult bindingResult = ex.getBindingResult();

        List<Problema.Field> problemFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    return Problema.Field.builder()
                            .name(convertToSnakeCase(fieldError.getField()))
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problema problem = createProblemBuilder(status, tipoProblema, detail)
                .fields(problemFields)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problem);
    }

    @ExceptionHandler(Exception.class )
    public ResponseEntity<?> handleInternalServerError(
            Exception ex){

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = "Erro interno de execução";
        TipoProblema problemType = TipoProblema.ERRO_DE_SISTEMA;

        Problema problema = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problema);
    }

    @ExceptionHandler({DuplicidadeNomeException.class, PrecoException.class,
            ProdutoOfertadoMenorZero.class, ProdutoInativoException.class, ProdutoPorcentagemNula.class} )
    public ResponseEntity<?> handleUnprocessableEntity(
            Exception ex){

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String detail = ex.getMessage();
        TipoProblema problemType = TipoProblema.ERRO_NEGOCIO;

        Problema problema = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problema);
    }

    @ExceptionHandler({DepartamentoInexistenteException.class, ProdutoInexistenteException.class})
    public ResponseEntity<?> handleBadRequest(
            Exception ex){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String detail = ex.getMessage();
        TipoProblema problemType = TipoProblema.DADO_INVALIDO;

        Problema problema = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problema);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleNotFound(
            EntidadeNaoEncontradaException ex, WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = ex.getMessage();
        TipoProblema problemType = TipoProblema.RECURSO_NAO_ENCONTRADO;

        Problema problema = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problema);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleConflict(
            EntidadeEmUsoException ex){

        HttpStatus status = HttpStatus.CONFLICT;
        String detail = ex.getMessage();
        TipoProblema problemType = TipoProblema.ENTIDADE_EM_USO;

        Problema problema = createProblemBuilder(status, problemType, detail)
                .userMessage(detail)
                .build();

        return ResponseEntity.status(status)
                .body(problema);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if(body == null){
            body = Problema.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }else if(body instanceof String){
            body = Problema.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status,
                                                          TipoProblema problemType, String detail){

        return Problema.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }

    private String convertToSnakeCase(String nomeCampo){
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return nomeCampo.replaceAll(regex, replacement).toLowerCase();
    }
}
