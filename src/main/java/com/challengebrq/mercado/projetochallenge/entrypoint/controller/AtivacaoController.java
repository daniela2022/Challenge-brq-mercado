package com.challengebrq.mercado.projetochallenge.entrypoint.controller;

import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request.AtivacaoEntryPointMapperRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response.AtivacaoEntryPointMapperResponse;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.AtivacaoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ValidList;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.AtivacaoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.service.AtivacaoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Validated
@RestController
@RequestMapping("v1/produtos/ativacoes")
public class AtivacaoController {

    private final AtivacaoUseCase ativacaoUseCase;

    public AtivacaoController(AtivacaoUseCase ativacaoUseCase) {
        this.ativacaoUseCase = ativacaoUseCase;
    }

    @PutMapping
    public ResponseEntity<?> atualizarAtivacao(
            @RequestBody @Valid ValidList<AtivacaoModelRequest> ativacaoModelRequestList) {

        List<Produto> produtoRequestDomain = AtivacaoEntryPointMapperRequest.convertList(ativacaoModelRequestList);

        ativacaoUseCase.atualizarAtivacao(produtoRequestDomain);

        if (produtoRequestDomain.isEmpty()) {
            ResponseEntity.badRequest().build();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<AtivacaoModelResponse>> listarAtivacoes() {

        List<Produto> produtoResponseDomain = ativacaoUseCase.listarAtivacao();

        List<AtivacaoModelResponse> ativacaoModelResponse = AtivacaoEntryPointMapperResponse.convertList(produtoResponseDomain);

        if (ativacaoModelResponse.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ativacaoModelResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarAtivacoes(@RequestBody @Valid ValidList<AtivacaoModelRequest> ativacaoModelRequest) {

        List<Produto> produtoRequestDomain = AtivacaoEntryPointMapperRequest.convertList(ativacaoModelRequest);

        ativacaoUseCase.deletarAtivacao(produtoRequestDomain);
        if (produtoRequestDomain.isEmpty()) {
            ResponseEntity.badRequest().build();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
