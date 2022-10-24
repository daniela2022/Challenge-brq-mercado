package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.*;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.OfertaGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new ProdutoInexistenteException
                        (String.format("Não existe cadastro de produto com código %s", idProduto)));
    }

    @Override
    public List<Produto> listarOferta() {
        return ofertaGateway.listarOfertas();
    }

    @Transactional
    @Override
    public void deletarOferta(List<Produto> produtos) {

        produtos.forEach(produto -> {
                    Produto produtoAtual = detalharProdutoPorId(produto.getId());
                    if(Objects.nonNull(produtoAtual.getOfertado())){
                        if(produtoAtual.getOfertado()){
                            produtoAtual.setOfertado(false);
                            produtoAtual.setPorcentagemOferta(0);
                        }
                    }
                    ofertaGateway.deletarOferta(produtoAtual);
                });
    }

//        for(Produto produto : produtos) {
//            Produto produtoAtual = detalharProdutoPorId(produto.getId());
//
//            if(Objects.nonNull(produtoAtual.getOfertado())){
//                if(produtoAtual.getOfertado()){
//                    produtoAtual.setOfertado(false);
//                    produtoAtual.setPorcentagemOferta(0);
//                }
//            }
//            ofertaGateway.deletarOferta(produtoAtual);
//        }
//    }

    @Transactional
    @Override
    public void atualizarOferta(List<Produto> produtos) {

        for(Produto produto : produtos){
            Produto produtoAtual = detalharProdutoPorId(produto.getId());

            if (!produtoAtual.getAtivo()) {
                throw new ProdutoInativoException
                        (String.format("O produto %s não pode ser ofertado, pois está inativo", produto.getId()));
            }

            if(produtoAtual.getAtivo() && !produtoAtual.getOfertado()){
                produtoAtual.setOfertado(true);
            }

            if(Objects.isNull(produto.getPorcentagemOferta())){
                throw new ProdutoPorcentagemNula("A porcentagem oferta não pode ser nula");
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
            throw new ProdutoOfertadoMenorZero(String.format("A porcentagem do produto %s não pode estar zerada ou negativa",
                    produtoRequest.getId()));
        }
    }

    private String getData() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return formato.format(date);
    }
}
