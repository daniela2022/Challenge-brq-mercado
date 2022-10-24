package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.*;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {
    @Autowired
    private final ProdutoGateway produtoGateway;
    @Autowired
    private final DepartamentoGateway departamentoGateway;

    public ProdutoUseCaseImpl(ProdutoGateway produtoGateway, DepartamentoGateway departamentoGateway) {
        this.produtoGateway = produtoGateway;
        this.departamentoGateway = departamentoGateway;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        validarCadastroProduto(produto);
        produto.setId(UUID.randomUUID().toString());
        produto.setAtivo(true);
        produto.setOfertado(false);
        produto.setDataCadastro(getData());
        produto.setPorcentagemOferta(0);

        return produtoGateway.criarProduto(produto);
    }

    @Override
    public List<Produto> listarProduto(String nome, String marca,Double preco, Integer departamento, Boolean ativo) {

        return produtoGateway.listarProdutos(nome, marca, preco, departamento, ativo);
    }

    @Override
    public Produto detalharProdutoPorId(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException
                        (String.format("Não existe cadastro de produto com código %s", idProduto)));
    }

    @Override
    public void deletarProduto(String idProduto) {
        Produto produto = detalharProdutoPorId(idProduto);
        if (produto.getAtivo()) {
            throw new ProdutoInativoException
                    (String.format("O produto %s não pode ser removido, pois está ativo", produto.getId()));
        }
        produtoGateway.deletarProdutoPorId(idProduto);
    }

    @Override
    public Produto atualizarParcialProduto(Produto produto) {
        Produto produtoAtual = detalharProdutoPorId(produto.getId());

        if(StringUtils.isNotBlank(produto.getNome())){
            Optional<Produto> produtoPresente = produtoGateway.buscarProdutoPorNome(produto.getNome());
            if(produtoPresente.isPresent() && !StringUtils.equalsIgnoreCase(produtoPresente.get().getId(), produto.getId())){
                validarDuplicidadeNomeProduto(produto);
            }
            produtoAtual.setNome(produto.getNome());
        }

        if (StringUtils.isNotBlank(produto.getDescricao())) {
            produtoAtual.setDescricao(produto.getDescricao());
        }

        if (StringUtils.isNotBlank(produto.getMarca())) {
            produtoAtual.setMarca(produto.getMarca());
        }
        if (Objects.nonNull(produto.getPreco())) {
            validarPrecoZeradoOuNegativo(produto);
            produtoAtual.setPreco(produto.getPreco());
        }

//        if (Objects.nonNull(produto.getPorcentagemOferta())) {
//            if (produto.getPorcentagemOferta() <= 0 && produto.getOfertado()) {
//                throw new ProdutoOfertadoMenorZero("A porcentagem da oferta não pode ser menor que zero");
//            }
//            produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
//        }
//
//        if (Objects.nonNull(produto.getAtivo())) {
//                if (!produto.getAtivo()) {
//                    produtoAtual.setPorcentagemOferta(0);
//                    produtoAtual.setOfertado(false);
//                }
//                produtoAtual.setAtivo(produto.getAtivo());
//        }
//
//        if (Objects.isNull(produto.getAtivo())) {
//            produto.setAtivo(produtoAtual.getAtivo());
//        }
//
//        if (Objects.nonNull(produto.getOfertado())) {
//            if ((produto.getOfertado() && produto.getPorcentagemOferta() == null)) {
//                throw new ProdutoPorcentagemNulo("A porcentagem oferta não pode ser nula");
//            }
//        }
//
//        if (Objects.nonNull(produto.getOfertado())) {
//            if(!produtoAtual.getOfertado() && produto.getAtivo()){
//                produtoAtual.setOfertado(produto.getOfertado());
//                produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
//            }
//        }
//
//        if (Objects.nonNull(produto.getOfertado())) {
//            if(!produto.getOfertado() && produto.getAtivo()){
//                produtoAtual.setOfertado(produto.getOfertado());
//                produtoAtual.setPorcentagemOferta(0);
//            }
//        }
//
//        if (Objects.isNull(produto.getOfertado())) {
//            produto.setOfertado(produtoAtual.getOfertado());
//        }
//
//        if (Objects.isNull(produto.getPorcentagemOferta())) {
//            produto.setPorcentagemOferta(produtoAtual.getPorcentagemOferta());
//        }

        validarDepartamento(produto);

        if(!produto.getDepartamentos().isEmpty()){
            produtoAtual.setDepartamentos(validarDepartamentoII(produto.getDepartamentos()));
        }

        if(produto.getDepartamentos() == null){
            produtoAtual.setDepartamentos(validarDepartamentoII(produto.getDepartamentos()));
        }

        produtoAtual.setDataAtualizacao(getData());

        return produtoGateway.atualizarParcialProduto(produtoAtual);
    }

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidadeNomeProduto(produto);
        validarPrecoZeradoOuNegativo(produto);
        validarDepartamento(produto);
    }

    private List<Departamento> validarDepartamentoII(List<Departamento> departamentos) {
            List<Departamento> departamentos1 = new ArrayList<>();
            departamentos.forEach(departamento -> {
                Optional<Departamento> departamentoPresente = departamentoGateway.buscarDepartamentoPorId(departamento.getId());
                if(departamentoPresente.isPresent())departamentos1.add(departamento);
            });
            return departamentos1;
    }


//            for (Departamento codigo : departamentos) {
//                Optional<Departamento> departamentoPresente = departamentoGateway.buscarDepartamentoPorId(codigo.getId());
//                if(departamentoPresente.isPresent()){
//                    departamentos1.add(codigo);
//                }
//            }
//            return departamentos1;
//

    private void validarDepartamento(Produto produto) {
            produto.getDepartamentos().forEach(departamento -> {
                departamentoGateway.buscarDepartamentoPorId(departamento.getId())
                        .orElseThrow(() -> new DepartamentoInexistenteException(String.format("Não existe cadastro de departamento com código %s", departamento.getId())));
            });

    }

    private void validarDuplicidadeNomeProduto(Produto produtoRequest) {
        produtoGateway.buscarProdutoPorNome(produtoRequest.getNome())
                .ifPresent(produto -> {
                    throw new DuplicidadeNomeException(String.format("O nome do produto '%s' não pode estar em duplicidade, o mesmo " +
                            "já tem cadastro no sistema", produtoRequest.getNome()));
                });
    }

    private void validarPrecoZeradoOuNegativo(Produto produtoRequest) {
        if (produtoRequest.getPreco() <= 0) {
            throw new PrecoException(String.format("O preço do produto não pode estar zerado ou negativo"
                    ));
        }
    }

    private String getData() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formato.format(date);
    }
}
