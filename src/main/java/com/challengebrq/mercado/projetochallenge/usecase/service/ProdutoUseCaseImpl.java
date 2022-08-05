package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.*;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public ProdutoUseCaseImpl(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
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
    public List<Produto> listarProduto() {

        return produtoGateway.listarProdutos();
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
            throw new ProdutoAtivoException
                    (String.format("O produto %s não pode ser removido, pois está ativo", produto.getId()));
        }
        produtoGateway.deletarProdutoPorId(idProduto);
    }

    @Override
    public Produto atualizarParcialProduto(Produto produto) {
        Produto produtoAtual = detalharProdutoPorId(produto.getId());

        if (StringUtils.isNotBlank(produto.getNome())) {
            Optional<Produto> produtoPresente = produtoGateway.buscarProdutoPorNome(produto.getNome());
            if (produtoPresente.isPresent() && !StringUtils.equalsIgnoreCase(produtoAtual.getId(), produto.getId())) {
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
        if (Objects.nonNull(produto.getAtivo())) {
            if (!produto.getAtivo()) {
                produtoAtual.setPorcentagemOferta(0);
                produtoAtual.setOfertado(false);
            }
            produtoAtual.setAtivo(produto.getAtivo());
        }

        if (Objects.isNull(produto.getAtivo())) {
            produto.setAtivo(produtoAtual.getAtivo());
        }

        if (Objects.nonNull(produto.getOfertado())) {
            if ((produto.getOfertado() && produto.getPorcentagemOferta() == null)) {
                throw new ProdutoPorcentagemNulo("A porcentagem oferta não pode ser nula");
            }
        }

        if (Objects.nonNull(produto.getOfertado())) {
            if(!produtoAtual.getOfertado() && produto.getAtivo()){
                produtoAtual.setOfertado(produto.getOfertado());
                produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
            }
        }

        if (Objects.isNull(produto.getOfertado())) {
            produto.setOfertado(produtoAtual.getOfertado());
        }

        if (Objects.nonNull(produto.getPorcentagemOferta())) {
            if (produto.getPorcentagemOferta() <= 0 && produto.getOfertado()) {
                throw new ProdutoOfertadoMenorZero("A porcentagem da oferta não pode ser menor que zero");
            }
        }

        if (Objects.isNull(produto.getPorcentagemOferta())) {
            produto.setPorcentagemOferta(produtoAtual.getPorcentagemOferta());
        }

        produtoAtual.setDataAtualizacao(getData());

        return produtoGateway.atualizarParcialProduto(produtoAtual);
    }

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidadeNomeProduto(produto);
        validarPrecoZeradoOuNegativo(produto);
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
            throw new PrecoException(String.format("O preço do produto '%s' não pode estar zerado ou negativo",
                    produtoRequest.getPreco()));
        }
    }

    private String getData() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formato.format(date);
    }
}
