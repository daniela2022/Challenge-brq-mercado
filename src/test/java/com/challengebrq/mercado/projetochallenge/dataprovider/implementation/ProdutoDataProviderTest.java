package com.challengebrq.mercado.projetochallenge.dataprovider.implementation;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.exceptions.CadastroException;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.ProdutoRepository;
import com.challengebrq.mercado.projetochallenge.usecase.domain.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ProdutoDataProviderTest {

    @InjectMocks
    private ProdutoDataProvider produtoDataProvider;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    void testeCadastrarProdutoSucesso() {
        ProdutoEntity produtoResponse = mockProdutoEntity();
        Produto produtoRequest = mockProduto();

        given(produtoRepository.save(Mockito.any())).willReturn(produtoResponse);

        Produto produtoDomain = produtoDataProvider.criarProduto(produtoRequest);

        assertNotNull(produtoDomain);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoDomain.getId()),
                () -> assertEquals("shampoo", produtoDomain.getNome()),
                () -> assertEquals("brilho intenso", produtoDomain.getDescricao()),
                () -> assertEquals("Kerastase", produtoDomain.getMarca()),
                () -> assertEquals(120.0, produtoDomain.getPreco()),
                () -> assertEquals("05/05/2017", produtoDomain.getDataCadastro()),
                () -> assertEquals("06/07/2018", produtoDomain.getDataAtualizacao()),
                () -> assertTrue(produtoDomain.getAtivo()),
                () -> assertFalse(produtoDomain.getOfertado()),
                () -> assertEquals(0, produtoDomain.getPorcentagemOferta())
        );
    }

    @Test
    void testeCadastrarProdutoFalha() {
        Produto produtoRequest = mockProduto();

        given(produtoRepository.save(Mockito.any())).willThrow(new RuntimeException((" Erro de comunicação com o banco.")));

        CadastroException produtoException = assertThrows(CadastroException.class,
                () -> produtoDataProvider.criarProduto(produtoRequest));

        assertEquals("Erro ao realizar o cadastro do produto", produtoException.getMessage());
    }

    @Test
    void testeBuscarProdutoPorNomeSucesso() {
        Produto produtoRequest = mockProduto();
        ProdutoEntity produtoResponse = mockProdutoEntity();

        given(produtoRepository.findByNomeProduto(produtoRequest.getNome()))
                .willReturn(Optional.of(produtoResponse));

        Optional<Produto> produto = produtoDataProvider.buscarProdutoPorNome(produtoRequest.getNome());

        assertTrue(produto.isPresent());
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get().getId()),
                () -> assertEquals("shampoo", produto.get().getNome()),
                () -> assertEquals("brilho intenso", produto.get().getDescricao()),
                () -> assertEquals("Kerastase", produto.get().getMarca()),
                () -> assertEquals(120.0, produto.get().getPreco()),
                () -> assertEquals("05/05/2017", produto.get().getDataCadastro()),
                () -> assertEquals("06/07/2018", produto.get().getDataAtualizacao()),
                () -> assertTrue(produto.get().getAtivo()),
                () -> assertFalse(produto.get().getOfertado()),
                () -> assertEquals(0, produto.get().getPorcentagemOferta()
                ));
    }

    @Test
    void testedetalharProdutoPorIdSucesso() {
        Produto produtoRequest = mockProduto();
        ProdutoEntity produtoResponse = mockProdutoEntity();

        given(produtoRepository.findById(produtoRequest.getId()))
                .willReturn(Optional.of(produtoResponse));

        Optional<Produto> produto = produtoDataProvider.detalharProdutoPorId(produtoRequest.getId());

        assertTrue(produto.isPresent());
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produto.get().getId())
                );
    }

    @Test
    void testeBuscaProdutoPorNomeRetornoVazio() {
        Produto produtoRequest = mockProduto();

        given(produtoRepository.findByNomeProduto(produtoRequest.getNome())).willReturn(Optional.empty());

        Optional<Produto> produto = produtoDataProvider.buscarProdutoPorNome(produtoRequest.getNome());

        assertTrue(produto.isEmpty());
    }

    @Test
    void testeListarProdutosSucesso() {
        var produtoResponse =  List.of(mockProdutoEntity());

        given(produtoRepository.findAll()).willReturn(produtoResponse);

        List<Produto> produtoDomain = produtoDataProvider.listarProdutos();

        assertNotNull(produtoDomain);
        assertAll(
                () -> assertEquals("3322c422-a336-4064-96b3-2fc39ea4a108", produtoDomain.get(0).getId()),
                () -> assertEquals("shampoo", produtoDomain.get(0).getNome()),
                () -> assertEquals("Kerastase", produtoDomain.get(0).getMarca()),
                () -> assertEquals(120.0, produtoDomain.get(0).getPreco())
        );
    }

    @Test
    void testeDeletarProdutoPorId(){
        Produto produtoRequest = mockProduto();
       produtoDataProvider.deletarProdutoPorId(produtoRequest.getId());

        verify(produtoRepository, times(1)).deleteById(produtoRequest.getId());
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
                .produtoOfertado(false)
                .produtoAtivo(true)
                .produtoPorcentagemOferta(0)
                .build();
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
}