package com.ifma.livraria.livro;

import com.ifma.livraria.entity.Livro;

public class LivroObjetosTest {

    public Livro getLivroTeste(){
        return new Livro(1L, "Mauricio de Souza", "Turma da monica", false, false);
    }
}
