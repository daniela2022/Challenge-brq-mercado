package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.entity.ProdutoEntity;
import com.challengebrq.mercado.projetochallenge.entrypoint.model.request.ProdutoModelRequest;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    private ProdutoModelRequest produtoModelRequest;


    @Override
    public List<ProdutoEntity> consultar(String nome, String marca,
                                         Double preco, Integer departamento,Boolean ativo) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<ProdutoEntity> criteria = builder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> root = criteria.from(ProdutoEntity.class);
        Join<ProdutoEntity, DepartamentoEntity> join = root.join("departamentos", JoinType.INNER);


        var predicates = new ArrayList<>();

        if(StringUtils.hasText(nome)){
            predicates.add(builder.like(root.get("nomeProduto"),"%" + nome + "%"));
        }
        if(StringUtils.hasText(marca)){
            predicates.add(builder.like(root.get("marcaProduto"), marca.trim()));
        }
        if (preco != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("precoProduto"),preco));
        }
        if (ativo != null){
            predicates.add(builder.equal(root.get("produtoAtivo"), ativo));
        }
        if(departamento != null){
            predicates.add(builder.equal(join.get("idDepartamento"), departamento));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<ProdutoEntity> query = manager.createQuery(criteria);
        return query.getResultList();
    }
}
