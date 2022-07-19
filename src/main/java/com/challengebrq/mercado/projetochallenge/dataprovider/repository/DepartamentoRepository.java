package com.challengebrq.mercado.projetochallenge.dataprovider.repository;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<DepartamentoEntity, Long> {

    Optional<DepartamentoEntity> findByNomeDepartamento(String nomeDepartamento);
}
