package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.ProdutoInexistenteException;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.AtivacaoGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AtivacaoUseCaseImpl implements AtivacaoUseCase {

    private final AtivacaoGateway ativacaoGateway;
    private final ProdutoGateway produtoGateway;

    public AtivacaoUseCaseImpl(AtivacaoGateway ativacaoGateway, ProdutoGateway produtoGateway) {
        this.ativacaoGateway = ativacaoGateway;
        this.produtoGateway = produtoGateway;
    }


    @Override
    public Produto detalharProdutoPorId(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(() -> new ProdutoInexistenteException
                        (String.format("Não existe cadastro de produto com código %s", idProduto)));
    }

    @Override
    public List<Produto> listarAtivacao() {
        return ativacaoGateway.listarAtivacoes();
    }

    @Transactional
    @Override
    public void atualizarAtivacao(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            produtoAtual.setAtivo(true);


            produtoAtual.setDataAtualizacao(getData());
            ativacaoGateway.atualizarAtivacao(produtoAtual);
        }
    }

    @Transactional
    @Override
    public void deletarAtivacao(List<Produto> produtos) {
        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            produtoAtual.setAtivo(false);
            produtoAtual.setOfertado(false);
            produtoAtual.setPorcentagemOferta(0);

            produtoAtual.setDataAtualizacao(getData());
            ativacaoGateway.deletarAtivacao(produtoAtual);
        }
    }

    private String getData() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formato.format(date);
    }
}
