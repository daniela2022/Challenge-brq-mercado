package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Departamento;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.*;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.DepartamentoGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProdutoUseCaseImplTest {

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private DepartamentoGateway departamentoGateway;


    @Test
    void testeCriarProdutoSucesso() {
        Produto produtoParaSerCriado = mockProdutoRequest(50.0);
        Produto produtoCriado = mockProdutoResponse();

        given(produtoGateway.buscarProdutoPorNome(produtoParaSerCriado.getNome())).willReturn(Optional.empty());
        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(produtoCriado.getDepartamentos().get(0)));
        given(produtoGateway.criarProduto(produtoParaSerCriado)).willReturn(produtoCriado);

        Produto produto = produtoUseCase.criarProduto(produtoParaSerCriado);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId()),
                () -> assertEquals("shampoo", produto.getNome()),
                () -> assertEquals("brilho intenso", produto.getDescricao()),
                () -> assertEquals(50, produto.getPreco()),
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta()),
                () -> assertEquals(1, produto.getDepartamentos().get(0).getId())
        );
    }

    @Test
    void testeCriarProdutoFalha() {
        Produto produtoParaSerCriado = mockProdutoRequest(50.0);

        given(produtoGateway.criarProduto(produtoParaSerCriado)).willReturn(null);

        Produto produto = produtoUseCase.criarProduto(produtoParaSerCriado);

        assertNull(produto);
    }

    @Test
    void testeCriarProdutoQuandoProdutoJaEstaCadastradoException() {
        Produto produtoRequest = mockProdutoRequest(10.0);

        given(produtoGateway.buscarProdutoPorNome(produtoRequest.getNome())).willReturn(Optional.of(mockProdutoResponse()));

        assertThrows(DuplicidadeNomeException.class, () -> produtoUseCase.criarProduto(produtoRequest));
    }

    @Test
    void testeCriarProdutoComPrecoPositivo() {
        Produto produtoParaSerCriado = mockProdutoRequest(5.0);

        assertEquals(5, produtoParaSerCriado.getPreco());
    }

    @Test
    void testeCriarProdutoComPrecoZeradoException() {
        Produto produtoParaSerCriado = mockProdutoRequest(0.0);

        assertThrows(PrecoException.class, () -> produtoUseCase.criarProduto(produtoParaSerCriado));
    }

    @Test
    void testeCriarProdutoComPrecoNegativoException() {
        Produto produtoParaSerCriado = mockProdutoRequest(-1.0);

        assertThrows(PrecoException.class, () -> produtoUseCase.criarProduto(produtoParaSerCriado));
    }

    @Test
    void testeCriarProdutoComDataCadastro() {
        Produto produtoParaSerCriado = mockProdutoRequestData("2022-07-05T16:27:22Z");

        assertEquals("2022-07-05T16:27:22Z", produtoParaSerCriado.getDataCadastro());
    }

//    @Test
//    void testeListarProduto() {
//        var produtoCriado = mockProdutoResponse();
//
//        given(produtoGateway.listarProdutos()).willReturn(List.of(produtoCriado));
//
//        List<Produto> produto = produtoUseCase.listarProduto();
//
//        assertNotNull(produto);
//        assertAll(
//                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get(0).getId()),
//                () -> assertEquals("shampoo", produto.get(0).getNome()),
//                () -> assertEquals("Kerastase", produto.get(0).getMarca()),
//                () -> assertEquals(50, produto.get(0).getPreco())
//        );
//    }

    @Test
    void testeDetalharProdutoPorId() {

        var produtoCriado = mockProdutoResponse();

        given(produtoGateway.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108")).willReturn(Optional.of(produtoCriado));

        Produto produto = produtoUseCase.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108");

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId())
        );
    }

    @Test
    void testeDetalharProdutoPorIdNulo() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> produtoUseCase.detalharProdutoPorId(null));
    }

    @Test
    void testeDeletarProdutoPorId() {
        var produtoCriado = mockProdutoRequestId(false);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));

        produtoUseCase.deletarProduto(produtoCriado.getId());

        verify(produtoGateway, times(1)).deletarProdutoPorId(produtoCriado.getId());
    }

    @Test
    void testeDeletarProdutoPorIdNulo() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> produtoUseCase.deletarProduto(null));
    }

    @Test
    void testeDeletarProdutoAtivo() {
        var produtoCriado = mockProdutoRequestId(true);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));

        assertThrows(ProdutoInativoException.class, () -> produtoUseCase.deletarProduto(produtoCriado.getId()));
    }

    @Test
    void testeAtualizarProdutoSucesso(){
        var produtoCriado = mockProdutoOfertado(true, true, 10);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(produtoCriado.getDepartamentos().get(0)));
        when(produtoGateway.atualizarParcialProduto(produtoCriado)).thenReturn(produtoCriado);

        Produto produtoAtual = produtoUseCase.atualizarParcialProduto(produtoCriado);

        assertNotNull(produtoAtual);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoAtual.getId()),
                () -> assertEquals("shampoo", produtoAtual.getNome()),
                () -> assertEquals("brilho intenso", produtoAtual.getDescricao()),
                () -> assertEquals("Kerastase", produtoAtual.getMarca()),
                () -> assertEquals(50, produtoAtual.getPreco()),
                () -> assertTrue(produtoAtual.getAtivo()),
                () -> assertTrue(produtoAtual.getOfertado()),
                () -> assertEquals(10, produtoAtual.getPorcentagemOferta()),
                () -> assertEquals(1, produtoAtual.getDepartamentos().get(0).getId())
        );
    }

//    @Test
//    void testeAtualizarValidacaoPorcentagemOfertaMenorZero(){
//        var produtoCriado = mockProdutoOfertado(true, true, 0);
//        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
//
//        assertThrows(ProdutoOfertadoMenorZero.class, () -> produtoUseCase.atualizarParcialProduto(produtoCriado));
//    }

//    @Test
//    void testeAtualizarProdutoInativo(){
//        var produtoCriado = mockProdutoOfertado(false, true, 10);
//        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
//        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(produtoCriado.getDepartamentos().get(0)));
//        when(produtoGateway.atualizarParcialProduto(produtoCriado)).thenReturn(produtoCriado);
//
//        Produto produtoAtual = produtoUseCase.atualizarParcialProduto(produtoCriado);
//
//        assertNotNull(produtoAtual);
//        assertAll(
//                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoAtual.getId()),
//                () -> assertEquals("shampoo", produtoAtual.getNome()),
//                () -> assertEquals("brilho intenso", produtoAtual.getDescricao()),
//                () -> assertEquals("Kerastase", produtoAtual.getMarca()),
//                () -> assertEquals(50, produtoAtual.getPreco()),
//                () -> assertFalse(produtoAtual.getAtivo()),
//                () -> assertFalse(produtoAtual.getOfertado()),
//                () -> assertEquals(0, produtoAtual.getPorcentagemOferta()),
//                () -> assertEquals(1, produtoAtual.getDepartamentos().get(0).getId())
//        );
//    }

    @Test
    void testeAtualizarOfertadoNulo(){
        var produtoAtual = mockProdutoAtualOfertado(true,null,10);
        var produtoCriado = mockProdutoOfertadoNulo(true,false,10);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(produtoCriado.getDepartamentos().get(0)));
        when(produtoGateway.atualizarParcialProduto(produtoCriado)).thenReturn(produtoCriado);

        Produto produto = produtoUseCase.atualizarParcialProduto(produtoAtual);

        assertNotNull(produto);
        assertAll(
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(10, produto.getPorcentagemOferta()),
                () -> assertEquals(1, produto.getDepartamentos().get(0).getId())
        );
    }

    @Test
    void testeAtualizarPorcentagemNulaOfertadoFalse(){
        var produtoAtual = mockProdutoAtualOfertado(true,false,null);
        var produtoCriado = mockProdutoOfertadoNulo(true,false,0);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
        given(departamentoGateway.buscarDepartamentoPorId(1)).willReturn(Optional.of(produtoCriado.getDepartamentos().get(0)));
        when(produtoGateway.atualizarParcialProduto(produtoCriado)).thenReturn(produtoCriado);

        Produto produto = produtoUseCase.atualizarParcialProduto(produtoAtual);

        assertNotNull(produto);
        assertAll(
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

//    @Test
//    void testeAtualizarProdutoPorcentagemNulaOfertadoTrue(){
//        var produtoCriado = mockProdutoOfertado(true, true, null);
//        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));
//
//        assertThrows(ProdutoPorcentagemNula.class, () -> produtoUseCase.atualizarParcialProduto(produtoCriado));
//    }

    private Produto mockProdutoOfertadoNulo(Boolean produtoAtivo, Boolean produtoOfertado, Integer porcentagemOferta) {
        return Produto.builder()
                .ativo(produtoAtivo)
                .ofertado(produtoOfertado)
                .porcentagemOferta(porcentagemOferta)
                .departamentos(List.of(mockDepartamento()))
                .build();
    }

    private Produto mockProdutoAtualOfertado(Boolean produtoAtivo, Boolean produtoOfertado, Integer porcentagemOferta) {
        return Produto.builder()
                .ativo(produtoAtivo)
                .ofertado(produtoOfertado)
                .porcentagemOferta(porcentagemOferta)
                .departamentos(List.of(mockDepartamento()))
                .build();
    }

    private Produto mockProdutoRequestData(String data) {
        return Produto.builder()
                .dataCadastro(data)
                .build();
    }

    private Produto mockProdutoRequest(Double preco) {
        return Produto.builder()
                .preco(preco)
                .departamentos(List.of())
                .build();
    }

    private Produto mockProdutoOfertado(Boolean produtoAtivo, Boolean produtoOfertado, Integer porcentagemOferta ) {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(50.0)
                .ofertado(produtoOfertado)
                .ativo(produtoAtivo)
                .porcentagemOferta(porcentagemOferta)
                .departamentos(List.of(mockDepartamento()))
                .build();
    }

    private Produto mockProdutoRequestId(Boolean ativo) {
        return Produto.builder()
                .id("12345")
                .ativo(ativo)
                .build();
    }

    private Produto mockProdutoResponse() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(50.0)
                .dataCadastro("15/05/2021")
                .ofertado(false)
                .ativo(true)
                .porcentagemOferta(0)
                .departamentos(List.of(mockDepartamento()))
                .build();
    }

    private Departamento mockDepartamento() {
        return Departamento.builder()
                .id(1)
                .build();
    }
}