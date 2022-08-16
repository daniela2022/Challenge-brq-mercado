package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request.OfertaRequestMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request.ProdutoRequestMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response.OfertaResponseMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response.ProdutoResponseMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.OfertaRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.OfertaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class OfertaDataProvider implements OfertaGateway {

    private final OfertaRepository ofertaRepository;

    @Transactional
    @Override
    public void atualizarOferta(Produto produto) {
        ProdutoEntity produtoAtual = ProdutoRequestMapper.converter(produto);
        ofertaRepository.save(produtoAtual);
    }

    @Override
    public List<Produto> listarOfertas() {
        List<ProdutoEntity> produtos = ofertaRepository.findByProdutoOfertadoTrue();
        return ProdutoResponseMapper.convert(produtos);
    }

    @Override
    public void deletarOferta(Produto produto) {
        ProdutoEntity produtoAtual = ProdutoRequestMapper.converter(produto);
        ofertaRepository.save(produtoAtual);
    }
}
