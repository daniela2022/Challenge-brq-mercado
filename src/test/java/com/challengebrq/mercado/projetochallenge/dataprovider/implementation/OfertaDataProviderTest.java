package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.OfertaRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class OfertaDataProviderTest {

    @InjectMocks
    private OfertaDataProvider ofertaDataProvider;

    @Mock
    private OfertaRepository ofertaRepository;

    @Test
    void testeAtualizarOferta(){
        ProdutoEntity produtoResponse = mockProdutoEntity();
        Produto produtoRequest = mockProduto();
        ofertaDataProvider.atualizarOferta(produtoRequest);

        verify(ofertaRepository, times(1)).save(produtoResponse);
    }

    @Test
    void testeListarOfertaSucesso() {
        var produtoResponse =  List.of(mockProdutoEntity());

        given(ofertaRepository.findByProdutoOfertadoTrue()).willReturn(produtoResponse);

        List<Produto> produtoDomain = ofertaDataProvider.listarOfertas();

        assertNotNull(produtoDomain);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoDomain.get(0).getId()),
                () -> assertEquals("shampoo", produtoDomain.get(0).getNome()),
                () -> assertEquals("Kerastase", produtoDomain.get(0).getMarca()),
                () -> assertEquals(120.0, produtoDomain.get(0).getPreco()),
                () -> assertTrue(produtoDomain.get(0).getOfertado()),
                () -> assertEquals(10, produtoDomain.get(0).getPorcentagemOferta())
        );
    }

    @Test
    void testeDeletarOferta(){
        ProdutoEntity produtoResponse = mockProdutoEntity();
        Produto produtoRequest = mockProduto();
        ofertaDataProvider.deletarOferta(produtoRequest);

        verify(ofertaRepository, times(1)).save(produtoResponse);
    }

    private Produto mockProduto() {
        return Produto.builder()
                .id("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nome("shampoo")
                .descricao("brilho intenso")
                .marca("Kerastase")
                .preco(120.0)
                .dataCadastro("05/05/2017")
                .dataAtualizacao("06/07/2018")
                .ofertado(false)
                .ativo(true)
                .porcentagemOferta(0)
                .build();
    }

    private ProdutoEntity mockProdutoEntity() {
        return ProdutoEntity.builder()
                .idProduto("3322c422-a336-4064-96b3-2fc39ea4a108")
                .nomeProduto("shampoo")
                .descricaoProduto("brilho intenso")
                .marcaProduto("Kerastase")
                .precoProduto(120.0)
                .dataCadastro("05/05/2017")
                .dataAtualizacao("06/07/2018")
                .produtoOfertado(true)
                .produtoAtivo(true)
                .produtoPorcentagemOferta(10)
                .build();
    }

}