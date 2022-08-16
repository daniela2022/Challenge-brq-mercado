package com.challengebrq.mercado.projetochallenge.usecase.service;

import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.EntidadeNaoEncontradaException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.PrecoException;
import com.challengebrq.mercado.projetochallenge.usecase.exceptions.ProdutoOfertadoMenorZero;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.OfertaGateway;
import com.challengebrq.mercado.projetochallenge.usecase.gateway.ProdutoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.util.List.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OfertaUseCaseImplTest {

    @InjectMocks
    private OfertaUseCaseImpl ofertaUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @Mock
    private OfertaGateway ofertaGateway;

    @Test
    void testeDetalharProdutoPorId() {

        var produtoCriado = mockProdutoResponse();

        given(produtoGateway.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108")).willReturn(Optional.of(produtoCriado));

        Produto produto = ofertaUseCase.detalharProdutoPorId("3322c422-a336-4064-96b3-2fc39ea4a108");

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.getId())
        );
    }

    @Test
    void testeDetalharProdutoPorIdNulo() {

        assertThrows(EntidadeNaoEncontradaException.class, () -> ofertaUseCase.detalharProdutoPorId(null));
    }

    @Test
    void testeListarOferta() {
        var produtoCriado = mockProdutoResponse();

        given(ofertaGateway.listarOfertas()).willReturn(of(produtoCriado));

        List<Produto> produto = ofertaUseCase.listarOferta();

        assertNotNull(produto);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get(0).getId()),
                () -> assertEquals("shampoo", produto.get(0).getNome()),
                () -> assertEquals("Kerastase", produto.get(0).getMarca()),
                () -> assertEquals(50, produto.get(0).getPreco())
        );
    }

    @Test
    void testeAtualizarValidacaoPorcentagemOfertaMenorZero(){
        var produtoCriado = mockProdutoOfertado(true, true, 0);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));

        assertThrows(ProdutoOfertadoMenorZero.class, () -> ofertaUseCase.atualizarOferta(List.of(produtoCriado)));
    }

    @Test
    void testeAtualizarOfertaSucesso(){
        var produtoCriado = mockProdutoOfertado(true, true, 10);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));

        ofertaUseCase.atualizarOferta(List.of(produtoCriado));

        verify(ofertaGateway, times(1)).atualizarOferta(produtoCriado);
    }

    @Test
    void testeDeletarOferta(){
        var produtoCriado = mockProdutoOfertado(true, true, 10);
        when(produtoGateway.detalharProdutoPorId(produtoCriado.getId())).thenReturn(Optional.of(produtoCriado));

        ofertaUseCase.deletarOferta(List.of(produtoCriado));

        verify(ofertaGateway, times(1)).deletarOferta(produtoCriado);
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