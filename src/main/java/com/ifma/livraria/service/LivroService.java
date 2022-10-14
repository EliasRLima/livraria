package com.ifma.livraria.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    public boolean livroEstaDisponivel(Long idLivro){
        return true;
    }

    public boolean livroEstaReservado(Long idLivro){
        return true;
    }

    public boolean LivroEstaEmprestado(Long idLivro){
        return true;
    }

    @Transactional
    public boolean setLivroEmprestado(Long idLivro){
        return true;
    }

    @Transactional
    public boolean setLivroReservado(Long idLivro){
        return true;
    }

    @Transactional
    public boolean setLivroDisponivel(Long idLivro){
        return true;
    }


}
