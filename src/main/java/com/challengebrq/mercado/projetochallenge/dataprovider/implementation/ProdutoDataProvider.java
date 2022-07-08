package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.request.ProdutoRequestMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.mapper.response.ProdutoResponseMapper;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.ProdutoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProdutoDataProvider implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Override
    public Optional<Produto> buscarProdutoPorNome(String nomeProduto) {

        return produtoRepository.findByNomeProduto(nomeProduto)
                .map(ProdutoResponseMapper::converter);
    }

    @Override
    public Produto criarProduto(Produto produto) {
        try {
            ProdutoEntity produtoCadastro = ProdutoRequestMapper.converter(produto);
            ProdutoEntity produtoCadastrado = produtoRepository.save(produtoCadastro);

            return ProdutoResponseMapper.converter(produtoCadastrado);
        } catch (Exception exception) {
            throw new CadastroException("Erro ao realizar o cadastro do produto", exception);
        }
    }

    @Override
    public List<Produto> listarProdutos() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();
        return ProdutoResponseMapper.convert(produtos);
    }

    @Override
    public Optional<Produto> detalharProdutoPorId(String id) {

        return produtoRepository.findById(id)
                .map(ProdutoResponseMapper::converter);
    }

    @Override
    public void deletarProdutoPorId(String idProduto) {

        produtoRepository.deleteById(idProduto);
    }
}




