package com.ifma.livraria.emprestimo;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.exceptions.LivrariaException;
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

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        Emprestimo realizadoRepositorio = null;
        when(repository.salvarNovoEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(realizadoRepositorio);
        Emprestimo realizadoService = service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest());

        assertEquals(realizadoRepositorio, realizadoService);
    }

    @Test
    public void realizarEmprestimoLivroReservado(){
        Throwable thrown = catchThrowable(() -> service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoComLivroReservadoTest()));

        assertThat(thrown).isInstanceOf(LivrariaException.class)
                .hasMessageContaining("O emprestimo nao obdece todas as regras.");
    }

    @Test
    public void verificarDataPrevistaEmprestimo(){
        assertTrue(service.dataPrevistaEmprestimoEstaValida(new EmprestimoObjetosTest().getEmprestimoTest()));

        Throwable thrown = catchThrowable(() -> service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoDataPrevistaInvalidaTest()));

        assertThat(thrown).isInstanceOf(LivrariaException.class)
                .hasMessageContaining("data prevista deve ser posterior a data de emprestimo");
    }

    @Test
    public void devolucaoAntesDaDataPrevista(){

    }

    @Test
    public void devolucaoNaDataPrevista(){

    }

    @Test
    public void devolucaoUmDiaAposDataPrevista(){

    }

    @Test
    public void devolucaoTrintaDiaAposDataPrevista(){

    }

}
