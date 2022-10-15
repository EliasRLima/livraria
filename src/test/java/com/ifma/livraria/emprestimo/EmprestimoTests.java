package com.ifma.livraria.emprestimo;

import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.exceptions.LivrariaException;
import com.ifma.livraria.repository.impl.EmprestimoRepositoryImpl;
import com.ifma.livraria.service.EmprestimoService;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
class EmprestimoTests {

    private static final LocalDate LOCAL_DATE = LocalDate.now();
    @Mock
    private EmprestimoRepositoryImpl repository;

    @InjectMocks
    private EmprestimoService service;

    @Mock
    private Clock clock;
    private Clock localClock;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void realizarEmprestimoLivroNaoReservado(){
        Emprestimo realizadoRepositorio = null;
        when(repository.salvarNovoEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(realizadoRepositorio);
        Emprestimo realizadoService = service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoDtoTest());

        assertEquals(realizadoRepositorio, realizadoService);
    }

    @Test
    public void realizarEmprestimoLivroReservado(){
        Throwable thrown = catchThrowable(() -> service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoDtoComLivroReservadoTest()));

        assertThat(thrown).isInstanceOf(LivrariaException.class)
                .hasMessageContaining("O emprestimo nao obdece todas as regras.");
    }

    @Test
    public void verificarDataPrevistaEmprestimo(){
        assertTrue(service.dataPrevistaEmprestimoEstaValida(new EmprestimoObjetosTest().getEmprestimoTest()));
        Throwable thrown = catchThrowable(() -> service.salvarEmprestimo(new EmprestimoObjetosTest().getEmprestimoDtoDataPrevistaInvalidaTest()));

        assertThat(thrown).isInstanceOf(LivrariaException.class)
                .hasMessageContaining("data prevista deve ser posterior a data de emprestimo");
    }


    @Test
    public void devolucaoAntesDaDataPrevista(){
        localClock = Clock.fixed(LOCAL_DATE.plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(localClock.instant()).when(clock).instant();
        doReturn(localClock.getZone()).when(clock).getZone();

        boolean retornoRepository = false;
        when(repository.devolucaoDeEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(retornoRepository);
        boolean retornoService = service.realizarDevolucao(new EmprestimoObjetosTest().getEmprestimoTest()) > 0;

        assertEquals(retornoService, retornoRepository);
    }

    @Test
    public void devolucaoNaDataPrevista(){
        localClock = Clock.fixed(LOCAL_DATE.plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(localClock.instant()).when(clock).instant();
        doReturn(localClock.getZone()).when(clock).getZone();

        boolean retornoRepository = false;
        when(repository.devolucaoDeEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(retornoRepository);
        boolean retornoService = service.realizarDevolucao(new EmprestimoObjetosTest().getEmprestimoTest()) > 0;

        assertEquals(retornoService, retornoRepository);
    }

    @Test
    public void devolucaoUmDiaAposDataPrevista(){
        localClock = Clock.fixed(LOCAL_DATE.plusDays(6).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(localClock.instant()).when(clock).instant();
        doReturn(localClock.getZone()).when(clock).getZone();

        boolean retornoRepository = false;
        when(repository.devolucaoDeEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(retornoRepository);
        double valorEmprestimo = service.realizarDevolucao(new EmprestimoObjetosTest().getEmprestimoTest());
        boolean multaAplicadaCorretamente = valorEmprestimo == (new EmprestimoObjetosTest().getEmprestimoTest().getLivros().size() * 5) + 0.4;

        assertEquals(multaAplicadaCorretamente, retornoRepository);
    }

    @Test
    public void devolucaoTrintaDiaAposDataPrevista(){
        localClock = Clock.fixed(LOCAL_DATE.plusDays(35).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(localClock.instant()).when(clock).instant();
        doReturn(localClock.getZone()).when(clock).getZone();

        boolean retornoRepository = false;
        when(repository.devolucaoDeEmprestimo(new EmprestimoObjetosTest().getEmprestimoTest())).thenReturn(retornoRepository);
        double valorEmprestimo = service.realizarDevolucao(new EmprestimoObjetosTest().getEmprestimoTest());
        boolean multaAplicadaCorretamente = valorEmprestimo == (new EmprestimoObjetosTest().getEmprestimoTest().getLivros().size() * 5) + 30*0.4;

        assertEquals(multaAplicadaCorretamente, retornoRepository);
    }

}
