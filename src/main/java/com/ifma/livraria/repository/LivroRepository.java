package com.ifma.livraria.repository;

import com.ifma.livraria.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository{

    public boolean livroEstaDisponivel(Long id_livro);
}
