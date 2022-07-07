package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.DuplicidadeNomeException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.PrecoException;
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

@ExtendWith(SpringExtension.class)
class ProdutoUseCaseImplTest {

    @InjectMocks
    private ProdutoUseCaseImpl produtoUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @Test
    void testeCriarProdutoSucesso() {
        Produto produtoParaSerCriado = mockProdutoRequest(50.0);
        Produto produtoCriado = mockProdutoResponse();

        given(produtoGateway.buscarProdutoPorNome(produtoParaSerCriado.getNome())).willReturn(Optional.empty());
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
                () -> assertEquals(0, produto.getPorcentagemOferta())
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

    @Test
    void testeListarProduto() {
        var produtoParaSerCriado = mockProdutoResquestLista();
        var produtoCriado = mockProdutoResponse();

        given(produtoGateway.listarProdutos(produtoParaSerCriado)).willReturn(List.of(produtoCriado));

        List<Produto> produto = produtoUseCase.listarProduto(produtoParaSerCriado);

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get(0).getId()),
                () -> assertEquals("shampoo", produto.get(0).getNome()),
                () -> assertEquals("Kerastase", produto.get(0).getMarca()),
                () -> assertEquals(50, produto.get(0).getPreco())
        );
    }

    @Test
    void testeDetalharProdutoPorId() {

        var produtoCriado = mockProdutoResponse();

        given(produtoGateway.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108")).willReturn(Optional.of(produtoCriado));

        Produto produto = produtoUseCase.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108");

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId()),
                () -> assertEquals("shampoo", produto.getNome()),
                () -> assertEquals("Kerastase", produto.getMarca()),
                () -> assertEquals("brilho intenso", produto.getDescricao()),
                () -> assertEquals(50, produto.getPreco()),
                () -> assertEquals("15/05/2021", produto.getDataCadastro()),
                () -> assertTrue(produto.getAtivo()),
                () -> assertFalse(produto.getOfertado()),
                () -> assertEquals(0, produto.getPorcentagemOferta())
        );
    }

    @Test
    void testeDetalharProdutoPorIdNulo() {
        Produto produtoParaSerCriado = mockProdutoRequestId(null);

        assertThrows(EntidadeNaoEncontradaException.class, () -> produtoUseCase.detalharProdutoPorId(null));
    }

    private Produto mockProdutoRequestData(String data) {
        return Produto.builder()
                .dataCadastro(data)
                .build();
    }

    private Produto mockProdutoRequest(Double preco) {
        return Produto.builder()
                .preco(preco)
                .build();
    }

    private Produto mockProdutoResquestLista() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .marca("Kerastase")
                .preco(50.0)
                .descricao("brilho")
                .build();
    }

    private Produto mockProdutoRequestId(String idProduto) {
        return Produto.builder()
                .id(idProduto)
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(50.0)
                .dataCadastro("15/05/2021")
                .ofertado(false)
                .ativo(true)
                .porcentagemOferta(0)
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
                .build();
    }
}