package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.response;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.response.ProdutoModelResponse;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProdutoEntryPointMapperResponse {

    public static ProdutoModelResponse converterProdutoParaModel(Produto produto){
        return ProdutoModelResponse.builder()
                .idProduto(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .dataCadastro(produto.getDataCadastro())
                .dataAtualizacao(produto.getDataAtualizacao())
                .ativo(produto.getAtivo())
                .ofertado(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .departamentos(DepartamentoEntryPointResponseMapper.convertList(produto.getDepartamentos()))
                .build();
    }

    public static ProdutoModelResponse converterLista(Produto produto){
        return ProdutoModelResponse.builder()
                .idProduto(produto.getId())
                .nome(produto.getNome())
                .marca(produto.getMarca())
                .preco(produto.getPreco())
                .ativo(produto.getAtivo())
                .departamentos(DepartamentoEntryPointResponseMapper.convertList(produto.getDepartamentos()))
                .build();
    }

    public static List<ProdutoModelResponse> convert(List<Produto> produtos){
        List<ProdutoModelResponse> produtosModelResponse = new ArrayList<>();

        produtos.forEach(produto -> {
            produtosModelResponse.add(converterLista((produto)));
        });
        return produtosModelResponse;
    }



}
