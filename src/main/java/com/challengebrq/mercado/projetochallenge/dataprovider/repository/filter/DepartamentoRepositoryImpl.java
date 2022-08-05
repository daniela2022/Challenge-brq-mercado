package com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter;

import com.challengebrq.mercado.projetochallenge.dataprovider.entity.DepartamentoEntity;
import com.challengebrq.mercado.projetochallenge.dataprovider.repository.DepartamentoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

import static com.challengebrq.mercado.projetochallenge.dataprovider.repository.filter.MercadoSpecs.comNomeSemelhante;

public class DepartamentoRepositoryImpl implements DepartamentoRepositoryQuery {

    @Autowired @Lazy
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoEntity> findListar(String nome) {

        if(StringUtils.isEmpty(nome)){
            return departamentoRepository.findAll();
        }
        return departamentoRepository.findAll(comNomeSemelhante(nome));

//        if(!StringUtils.isEmpty(nome)){
//            return departamentoRepository.findAll(comNomeSemelhante(nome));
//        }else {
//            return departamentoRepository.findAll();
//        }

    }

}
