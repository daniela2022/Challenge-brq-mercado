package com.challengebrq.mercado.projetochallenge.dataprovider.repository;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter.ProdutoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String>, ProdutoRepositoryQuery, JpaSpecificationExecutor<ProdutoEntity> {

    Optional<ProdutoEntity> findByNomeProduto(String nomeProduto);

    //List<ProdutoEntity> consultar(String nome, String marca, Double preco, Integer departamento);

//    @Query("from ProdutoEntity where nome like %:nome% and marca = :marca and departamento.id = :id")
//    List<ProdutoEntity> consultar(String nome, String marca,Double preco, @Param("id") Integer departamento);
}
