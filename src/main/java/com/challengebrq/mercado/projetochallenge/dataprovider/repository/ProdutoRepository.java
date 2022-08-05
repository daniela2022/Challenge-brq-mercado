package com.challengebrq.mercado.projetochallenge.dataprovider.repository;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {

    Optional<ProdutoEntity> findByNomeProduto(String nomeProduto);

}
