package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request.ProdutoRequestMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response.ProdutoResponseMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.AtivacaoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.AtivacaoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@AllArgsConstructor
public class AtivacaoDataProvider implements AtivacaoGateway {

    private final AtivacaoRepository ativacaoRepository;

    @Transactional
    @Override
    public void atualizarAtivacao(Produto produto) {
        ProdutoEntity produtoAtual = ProdutoRequestMapper.convert(produto);
        ativacaoRepository.save(produtoAtual);
    }

    @Override
    public List<Produto> listarAtivacoes() {
        List<ProdutoEntity> produtos = ativacaoRepository.findByProdutoAtivoTrue();
        return ProdutoResponseMapper.convert(produtos);
    }

    @Transactional
    @Override
    public void deletarAtivacao(Produto produto) {
        ProdutoEntity produtoAtual = ProdutoRequestMapper.converter(produto);
        ativacaoRepository.save(produtoAtual);
    }
}
