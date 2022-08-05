package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import org.springframework.data.jpa.domain.Specification;

public class MercadoSpecs {

    public static Specification<DepartamentoEntity> comNomeSemelhante(String nome){
        return ((root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%"));
    }

}
