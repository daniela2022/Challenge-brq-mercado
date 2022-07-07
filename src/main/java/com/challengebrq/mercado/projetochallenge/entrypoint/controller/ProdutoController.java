package com.challengebrq.mercado.projetochallenge.entrypoint.controller;

import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelFiltroRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.ProdutoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.service.ProdutoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/produtos")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;

    public ProdutoController(ProdutoUseCase produtoUseCase) {
        this.produtoUseCase = produtoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProdutoModelResponse> adicionarProduto(
            @RequestBody ProdutoModelRequest produtoModelRequest) {

        Produto produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(produtoModelRequest);
        Produto produtoResponseDomain = produtoUseCase.criarProduto(produtoRequestDomain);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converter(produtoResponseDomain);

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutos(ProdutoModelFiltroRequest produtoModelFiltroRequest) {
        Produto produtoRequestDomain = ProdutoEntryPointMapperRequest.convert(produtoModelFiltroRequest);
        List<Produto> produtoResponseDomain = produtoUseCase.listarProduto(produtoRequestDomain);

        List<ProdutoModelResponse> produtoModelResponse = ProdutoEntryPointMapperResponse.convert(produtoResponseDomain);

        if (produtoModelResponse.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{idProduto}")
    public ResponseEntity<ProdutoModelResponse> detalharProdutoPorId(@PathVariable String idProduto) {

        Produto produto = produtoUseCase.detalharProdutoPorId(idProduto);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converter(produto);

        if (produtoModelResponse.getIdProduto().isEmpty()) {
            ResponseEntity.notFound().build();

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idProduto}")
    public ResponseEntity<?> deletarProdutoPorId(@PathVariable String idProduto) {
        produtoUseCase.deletarProduto(idProduto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}








