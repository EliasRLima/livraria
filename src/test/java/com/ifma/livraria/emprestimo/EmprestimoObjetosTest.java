package com.ifma.livraria.emprestimo;

import com.ifma.livraria.dto.EmprestimoDTO;
import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.entity.Livro;
import com.ifma.livraria.livro.LivroObjetosTest;
import com.ifma.livraria.usuario.UsuarioObjetosTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoObjetosTest {

    public EmprestimoDTO getEmprestimoDtoTest(){
        EmprestimoDTO emprestimo = new EmprestimoDTO();
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataPrevistaDevolucaoEmprestimo(LocalDateTime.now().plusDays(5));
        return emprestimo;
    }

    public Emprestimo getEmprestimoTest(){
        Emprestimo emprestimo = new Emprestimo();
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataInicioEmprestimo(LocalDateTime.now());
        emprestimo.setDataPrevistaDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(null);
        return emprestimo;
    }

    public EmprestimoDTO getEmprestimoDtoAtrasadoTest(){
        EmprestimoDTO emprestimo = new EmprestimoDTO();
        emprestimo.setIdEmprestimo(1L);
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataPrevistaDevolucaoEmprestimo(LocalDateTime.now().plusDays(5));
        return emprestimo;
    }

    public EmprestimoDTO getEmprestimoDtoDataPrevistaInvalidaTest(){
        EmprestimoDTO emprestimo = new EmprestimoDTO();
        emprestimo.setIdEmprestimo(1L);
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataPrevistaDevolucaoEmprestimo(LocalDateTime.now().minusDays(5));
        return emprestimo;
    }


    public Emprestimo getEmprestimoDataPrevistaInvalidaTest(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataPrevistaDevolucaoEmprestimo(LocalDateTime.now());
        emprestimo.setDataInicioEmprestimo(emprestimo.getDataPrevistaDevolucaoEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(null);
        return emprestimo;
    }

    public EmprestimoDTO getEmprestimoDtoComLivroReservadoTest(){
        EmprestimoDTO emprestimo = new EmprestimoDTO();
        emprestimo.setIdEmprestimo(1L);
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        livros.add(new LivroObjetosTest().getLivroReservadoTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataPrevistaDevolucaoEmprestimo(LocalDateTime.now().plusDays(5));
        return emprestimo;
    }

    public Emprestimo getEmprestimoComLivroReservadoTest(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        List<Livro> livros = new ArrayList<>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        livros.add(new LivroObjetosTest().getLivroReservadoTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataInicioEmprestimo(LocalDateTime.now());
        emprestimo.setDataPrevistaDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(null);
        return emprestimo;
    }

    public Emprestimo getEmprestimoComLivroEmprestadoTest(){
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        List livros = new ArrayList<Livro>();
        livros.add(new LivroObjetosTest().getLivroTeste());
        livros.add(new LivroObjetosTest().getLivroEmprestadoTeste());
        emprestimo.setIdUser(new UsuarioObjetosTest().getUsuarioTeste().getId());
        emprestimo.setLivros(livros);
        emprestimo.setDataInicioEmprestimo(LocalDateTime.now());
        emprestimo.setDataPrevistaDevolucaoEmprestimo(emprestimo.getDataInicioEmprestimo().plusDays(5));
        emprestimo.setDataDevolucaoEmprestimo(null);
        return emprestimo;
    }

}
