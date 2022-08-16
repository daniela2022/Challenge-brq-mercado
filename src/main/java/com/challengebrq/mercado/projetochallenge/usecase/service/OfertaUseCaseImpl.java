package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.ProdutoAtivoException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.ProdutoOfertadoMenorZero;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.OfertaGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OfertaUseCaseImpl implements OfertaUseCase{

    private final OfertaGateway ofertaGateway;
    private final ProdutoGateway produtoGateway;

    public OfertaUseCaseImpl(OfertaGateway ofertaGateway, ProdutoGateway produtoGateway) {
        this.ofertaGateway = ofertaGateway;
        this.produtoGateway = produtoGateway;
    }


    @Override
    public Produto detalharProdutoPorId(String idProduto) {
        return produtoGateway.detalharProdutoPorId(idProduto)
                .orElseThrow(() -> new EntidadeNaoEncontradaException
                        (String.format("Não existe cadastro de produto com código %s", idProduto)));
    }

    @Override
    public List<Produto> listarOferta() {
        return ofertaGateway.listarOfertas();
    }

    @Override
    public void deletarOferta(List<Produto> produtos) {

        for(Produto produto : produtos) {
            Produto produtoAtual = detalharProdutoPorId(produto.getId());


            if(Objects.nonNull(produtoAtual.getOfertado())){
                if(produtoAtual.getOfertado()){
                    produtoAtual.setOfertado(false);
                    produtoAtual.setPorcentagemOferta(0);
                }
            }
            ofertaGateway.deletarOferta(produtoAtual);
        }
    }

    @Override
    public void atualizarOferta(List<Produto> produtos) {

        for(Produto produto : produtos){
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            if (Objects.nonNull(produto.getAtivo())) {
                if (!produto.getAtivo()) {
                    throw new ProdutoAtivoException
                            (String.format("O produto %s não pode ser ofertado, pois está inativo", produto.getId()));
                }
            }

            if (Objects.nonNull(produto.getPorcentagemOferta())) {
                validarPorcentagemZeradoOuNegativo(produto);
                produtoAtual.setPorcentagemOferta(produto.getPorcentagemOferta());
            }

            if(Objects.nonNull(produtoAtual.getOfertado())){
                if(!produtoAtual.getOfertado()){
                    produtoAtual.setPorcentagemOferta(0);
                }
            }

            produtoAtual.setDataAtualizacao(getData());
            ofertaGateway.atualizarOferta(produtoAtual);
        }

    }

    private void validarPorcentagemZeradoOuNegativo(Produto produtoRequest) {
        if (produtoRequest.getPorcentagemOferta() <= 0) {
            throw new ProdutoOfertadoMenorZero(String.format("A porcentagem da oferta não pode ser menor que zero",
                    produtoRequest.getPorcentagemOferta()));
        }
    }

    private String getData() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formato.format(date);
    }
}
