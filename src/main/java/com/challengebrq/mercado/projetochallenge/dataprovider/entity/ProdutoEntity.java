package com.challengebrq.mercado.projetochallenge.dataprovider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Table(name = "produtos")
public class ProdutoEntity {

    @EqualsAndHashCode.Include
    @Id
    private String idProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false)
    private String descricaoProduto;

    @Column(nullable = false)
    private String marcaProduto;

    @Column(nullable = false)
    private Double precoProduto;

    @Column(nullable = false)
    private String dataCadastro;

    @Column(nullable = true)
    private String dataAtualizacao;

    @Column(nullable = false)
    private Boolean produtoAtivo;

    @Column(nullable = false)
    private Boolean produtoOfertado;

    @Column(nullable = false)
    private Integer produtoPorcentagemOferta;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "produto_departamento",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private List<DepartamentoEntity> departamentos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdutoEntity that = (ProdutoEntity) o;
        return idProduto != null && Objects.equals(idProduto, that.idProduto);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
