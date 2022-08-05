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
                .nomeProduto(produto.getNome())
                .descricaoProduto(produto.getDescricao())
                .marcaProduto(produto.getMarca())
                .precoProduto(produto.getPreco())
                .dataCadastro(produto.getDataCadastro())
                .dataAtualizacao(produto.getDataAtualizacao())
                .ativoProduto(produto.getAtivo())
                .ofertadoProduto(produto.getOfertado())
                .porcentagemOferta(produto.getPorcentagemOferta())
                .build();
    }

    public static ProdutoModelResponse converterLista(Produto produto){
        return ProdutoModelResponse.builder()
                .idProduto(produto.getId())
                .nomeProduto(produto.getNome())
                .marcaProduto(produto.getMarca())
                .precoProduto(produto.getPreco())
                .build();
    }

    public static ProdutoModelResponse converterOferta(Produto produto){
        return ProdutoModelResponse.builder()
                .idProduto(produto.getId())
                .porcentagemOferta(produto.getPorcentagemOferta())
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
