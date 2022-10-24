package com.challengebrq.mercado.projetochallenge.entrypoint.controller;

import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request.OfertaEntryPointMapperRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response.OfertaEntryPointMapperResponse;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.OfertaModelRequestRemover;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ValidList;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.OfertaModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.service.OfertaUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Validated
@RestController
@RequestMapping("v1/ofertas")
public class OfertaController {


    private final OfertaUseCase ofertaUseCase;

    public OfertaController(OfertaUseCase ofertaUseCase) {
        this.ofertaUseCase = ofertaUseCase;
    }


    @PutMapping
    public ResponseEntity<?> atualizarOferta(
            @RequestBody @Valid ValidList<OfertaModelRequest> ofertaModelRequest) {

        List<Produto> produtoRequestDomain = OfertaEntryPointMapperRequest.convert(ofertaModelRequest);

        ofertaUseCase.atualizarOferta(produtoRequestDomain);

        if (produtoRequestDomain.isEmpty()) {
            ResponseEntity.badRequest().build();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<OfertaModelResponse>> listarOfertas() {

        List<Produto> produtoResponseDomain = ofertaUseCase.listarOferta();

        List<OfertaModelResponse> ofertaModelResponse = OfertaEntryPointMapperResponse.convert(produtoResponseDomain);

        if (ofertaModelResponse.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ofertaModelResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletarOferta(@RequestBody @Valid ValidList<OfertaModelRequestRemover> ofertaModelRequestRemover) {

        List<Produto> produtoRequestDomain = OfertaEntryPointMapperRequest.convertLista(ofertaModelRequestRemover);

        ofertaUseCase.deletarOferta(produtoRequestDomain);

        if (produtoRequestDomain.isEmpty()) {
            ResponseEntity.badRequest().build();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

