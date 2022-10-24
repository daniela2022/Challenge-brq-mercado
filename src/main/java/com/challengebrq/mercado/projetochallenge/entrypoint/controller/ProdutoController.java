package com.challengebrq.mercado.projetochallenge.entrypoint.controller;

import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequestAtualizar;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.ProdutoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.service.ProdutoUseCase;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            @RequestBody @Valid ProdutoModelRequest produtoModelRequest) {

        Produto produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(produtoModelRequest);
        Produto produtoResponseDomain = produtoUseCase.criarProduto(produtoRequestDomain);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterProdutoParaModel(produtoResponseDomain);

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutos(@RequestParam(value="nome", required=false)String nome,
                                                                     @RequestParam(value="marca", required=false)String marca,
                                                                     @RequestParam(value="preco", required=false)Double preco,
                                                                     @RequestParam(value="departamento" , required=false) Integer departamento,
                                                                     @RequestParam(value="ativo", required=false)Boolean ativo) {

        List<Produto> produtoResponseDomain = produtoUseCase.listarProduto(nome, marca, preco, departamento, ativo);

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

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProdutoParaModel(produto);

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

    @PatchMapping("/{idProduto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProduto(
            @PathVariable(value = "idProduto") String idProduto,
             @RequestBody @Valid ProdutoModelRequestAtualizar produtoModelRequestAtualizar) {

        Produto produtoRequestDomain = ProdutoEntryPointMapperRequest.convert(idProduto,produtoModelRequestAtualizar);
        Produto produtoResponseDomain = produtoUseCase.atualizarParcialProduto(produtoRequestDomain);

        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse
                .converterProdutoParaModel(produtoResponseDomain);

        if (produtoModelResponse.getIdProduto().isEmpty()) {
            ResponseEntity.notFound().build();

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }
}








