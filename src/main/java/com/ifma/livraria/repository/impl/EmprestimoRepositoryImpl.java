package com.ifma.livraria.repository.impl;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.repository.AbstractRepository;
import com.ifma.livraria.repository.EmprestimoRepository;
import com.ifma.livraria.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class EmprestimoRepositoryImpl extends AbstractRepository implements EmprestimoRepository {

    public EmprestimoRepositoryImpl(JdbcTemplate jdbcTemplate, QueryService queryService, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, queryService, namedParameterJdbcTemplate);
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        return new Emprestimo();
    }
}
