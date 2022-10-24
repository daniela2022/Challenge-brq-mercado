package com.challengebrq.mercado.projetochallenge.entrypoint.mapper.request;

import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequestAtualizar;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class ProdutoEntryPointMapperRequest {

    @Autowired
    private static DepartamentoGateway departamentoGateway;

    public static Produto converter(ProdutoModelRequest produtoModelRequest){
        return Produto.builder()
                .nome(produtoModelRequest.getNome())
                .descricao(produtoModelRequest.getDescricao())
                .marca(produtoModelRequest.getMarca())
                .preco(produtoModelRequest.getPreco())
                .departamentos(convert(produtoModelRequest.getCodigosDepartamento()))
                .build();
    }

    private static List<Departamento> convert(List<Integer> codigoDepartamento) {
        List<Departamento> departamentos = new ArrayList<>();
        for (Integer codigo : codigoDepartamento) {

           Departamento departamento = Departamento.builder()
                   .id(codigo)
                   .build();

           departamentos.add(departamento);
        }
        return departamentos;
    }

    public static Produto convert(String idProduto){
        return Produto.builder()
                .id(idProduto)
                .build();
    }

    public static Produto convert(String idProduto, ProdutoModelRequestAtualizar produtoModelRequestAtualizar) {

        return Produto.builder()
                .id(idProduto)
                .nome(produtoModelRequestAtualizar.getNome())
                .descricao(produtoModelRequestAtualizar.getDescricao())
                .marca(produtoModelRequestAtualizar.getMarca())
                .preco(produtoModelRequestAtualizar.getPreco())
//                .ativo(produtoModelRequestAtualizar.getAtivoProduto())
//                .ofertado(produtoModelRequestAtualizar.getOfertadoProduto())
//                .porcentagemOferta(produtoModelRequestAtualizar.getPorcentagemOferta())
                .departamentos(convertId(produtoModelRequestAtualizar.getIdsDepartamento()))
                .build();
    }

//    private static void validarDepartamentoII(List<Integer> idsDepartamento) {
//        if (Objects.nonNull(idsDepartamento)) {
//            for (Integer codigo : idsDepartamento) {
//                Optional<Departamento> departamentoPresente = departamentoGateway.buscarDepartamentoPorId(codigo);
//                if(departamentoPresente.isPresent()){
//                    idsDepartamento.add(codigo);
//                }
//            }
//        }
//    }


//    if(StringUtils.isNotBlank(produto.getNome())){
//        Optional<Produto> produtoPresente = produtoGateway.buscarProdutoPorNome(produto.getNome());
//        if(produtoPresente.isPresent() && !StringUtils.equalsIgnoreCase(produtoPresente.get().getId(), produto.getId())){
//            validarDuplicidadeNomeProduto(produto);
//        }
//        produtoAtual.setNome(produto.getNome());
//    }

    private static List<Departamento> convertId(List<Integer> idDepartamento) {
        List<Departamento> departamentos = new ArrayList<>();
        if(Objects.nonNull(idDepartamento)){
            for (Integer codigo : idDepartamento) {

                Departamento departamento = Departamento.builder()
                        .id(codigo)
                        .build();

                departamentos.add(departamento);
            }
        }
        return departamentos;
    }

}
