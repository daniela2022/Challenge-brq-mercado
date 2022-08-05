package com.challengebrq.mercado.projetochallenge.entrypoint.controller;

import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request.DepartamentoEntryPointMapperRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response.DepartamentoEntryPointResponseMapper;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.DepartamentoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.DepartamentoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.service.DepartamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/departamentos")
public class DepartamentoController {

    private final DepartamentoUseCase departamentoUseCase;

    public DepartamentoController(DepartamentoUseCase departamentoUseCase) {
        this.departamentoUseCase = departamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<DepartamentoModelResponse> cadastrarDepartamento(
            @RequestBody DepartamentoModelRequest departamentoModelRequest) {

        Departamento departamentoRequestDomain = DepartamentoEntryPointMapperRequest.converter(departamentoModelRequest);
        Departamento departamentoResponseDomain = departamentoUseCase.criarDepartamento(departamentoRequestDomain);

        DepartamentoModelResponse departamentoModelResponse = DepartamentoEntryPointResponseMapper
                .converterDepartamentoParaModel(departamentoResponseDomain);


        return new ResponseEntity<>(departamentoModelResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoModelResponse>> listarDepartamentos(@RequestParam(value="nome", required=false) String nome) {

        List<Departamento> departamentosResponseDomain = departamentoUseCase.listarDepartamento(nome);

        List<DepartamentoModelResponse> departamentoModelResponse = DepartamentoEntryPointResponseMapper.convert(departamentosResponseDomain);

        if (departamentoModelResponse.isEmpty()) {
            ResponseEntity.noContent().build();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(departamentoModelResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{idDepartamento}")
    public ResponseEntity<?> deletarDepartamentoPorId(@PathVariable Long idDepartamento) {
        departamentoUseCase.deletarDepartamento(idDepartamento);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
