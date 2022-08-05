package com.challengebrq.mercado.projetochallenge.dataprovider.repository;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter.DepartamentoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> , DepartamentoRepositoryQuery, JpaSpecificationExecutor<DepartamentoEntity> {

    Optional<DepartamentoEntity> findByNome(String nomeDepartamento);
}
