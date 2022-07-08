package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.DuplicidadeNomeException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.PrecoException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.ProdutoAtivoException;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private void validarCadastroProduto(Produto produto) {
        validarDuplicidadeNomeProduto(produto);
        validarPrecoZeradoOuNegativo(produto);
        validarDataCadastro(produto);
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

    private void validarDataCadastro(Produto produtoRequest) {
        String dataCadastro = null;
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dataCadastro = formato.format(date);
        produtoRequest.setDataCadastro(dataCadastro);
    }
}
