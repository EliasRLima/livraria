package com.ifma.livraria.service;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.repository.impl.EmprestimoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepositoryImpl emprestimoRepository;

    @Transactional
    public Emprestimo salvarEmprestimo(Emprestimo emprestimo){
        return  emprestimoRepository.salvarNovoEmprestimo(emprestimo) ;
    }
}
