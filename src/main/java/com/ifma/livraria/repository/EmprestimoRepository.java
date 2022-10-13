package com.ifma.livraria.repository;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.entity.EmprestimoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoPK> {
}
