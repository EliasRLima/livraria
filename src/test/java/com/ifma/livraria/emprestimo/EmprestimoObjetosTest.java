package com.ifma.livraria.emprestimo;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.entity.Livro;
import com.ifma.livraria.entity.Usuario;
import com.ifma.livraria.livro.LivroObjetosTest;
import com.ifma.livraria.usuario.UsuarioObjetosTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoObjetosTest {

    public Emprestimo getEmprestimoTest(){
        Emprestimo emprestimo = new Emprestimo();
        List livros = new ArrayList<Livro>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataInicioEmprestimo(LocalDateTime.now());
        emprestimo.setDataPrevistaDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        return emprestimo;
    }

    public Emprestimo getEmprestimoAtrasadoTest(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        List livros = new ArrayList<Livro>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataInicioEmprestimo(LocalDateTime.now());
        emprestimo.setDataPrevistaDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(6));
        return emprestimo;
    }

}
