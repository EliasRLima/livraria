package com.ifma.livraria.livro;

import com.ifma.livraria.entity.Livro;

public class LivroObjetosTest {

    public Livro getLivroTeste(){
        return new Livro(1L, "Mauricio de Souza", "Turma da monica", false, false);
    }

    public Livro getLivroReservadoTeste(){
        return new Livro(2L, "Joao do Pneu", "A borracha e o diamante", false, true);
    }

    public Livro getLivroEmprestadoTeste(){
        return new Livro(3L, "Luis José", "O amanhã vem de uma flor", true, false);
    }
}
