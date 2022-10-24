package com.challengebrq.mercado.projetochallenge.dataprovider.repository;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtivacaoRepository extends JpaRepository<ProdutoEntity, String> {

    List<ProdutoEntity> findByProdutoAtivoTrue();
}
