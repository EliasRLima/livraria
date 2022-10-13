package com.ifma.livraria.emprestimo;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.repository.impl.EmprestimoRepositoryImpl;
import com.ifma.livraria.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
class EmprestimoTests {

    @Mock
    private EmprestimoRepositoryImpl repository;

    @InjectMocks
    private EmprestimoService service;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void realizarEmprestimoLivroNaoReservado(){
        Emprestimo realizado = null;
        Emprestimo emprestimo = new EmprestimoObjetosTest().getEmprestimoTest();
        when(repository.salvar(emprestimo)).thenReturn(realizado);
        Emprestimo realizadoService = service.salvarEmprestimo(emprestimo);

        assertEquals(realizado, realizadoService);
    }
}
