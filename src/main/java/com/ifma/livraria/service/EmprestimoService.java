package com.ifma.livraria.service;

import com.ifma.livraria.dto.EmprestimoDTO;
import com.ifma.livraria.entity.Emprestimo;
import com.ifma.livraria.entity.Livro;
import com.ifma.livraria.exceptions.LivrariaException;
import com.ifma.livraria.repository.impl.EmprestimoRepositoryImpl;
import com.ifma.livraria.utils.MessageProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepositoryImpl emprestimoRepository;

    @Autowired
    private Clock clock;

    private LivroService livroService;

    private UsuarioService usuarioService;

    public EmprestimoService() {
        this.livroService = new LivroService();
        this.usuarioService = new UsuarioService();
    }

    @Transactional
    public Emprestimo salvarEmprestimo(EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = emprestimoDTO.converterParaEmprestimo();
        if(!emprestimoValido(emprestimo)){
            throw new LivrariaException(
                    MessageProperties.getMensagemPadrao("emprestimo.invalido"));
        }
        return  emprestimoRepository.salvarNovoEmprestimo(emprestimo) ;
    }

    @Transactional
    public double realizarDevolucao(Emprestimo emprestimo){
        emprestimo.setDataDevolucaoEmprestimo(LocalDateTime.now(clock));
        boolean devolver = emprestimoRepository.devolucaoDeEmprestimo(emprestimo);
        return devolver ? calculaValorEmprestimo(emprestimo) : 0;
    }

    public boolean emprestimoValido(Emprestimo emprestimo){
        List<Livro> livroIndisponivel = emprestimo.getLivros().stream().filter(x -> !livroService.livroEstaDisponivel(x)).collect(Collectors.toList());

        return dataDevolucaoEmprestimoEstaValida(emprestimo)
                && dataPrevistaEmprestimoEstaValida(emprestimo)
                && usuarioService.usuarioLiberadoParaEmprestimo(emprestimo.getIdUser())
                && livroIndisponivel.isEmpty()
                && limiteQuantidadeDeLivrosNaoUltrapassada(emprestimo);
    }

    public boolean dataPrevistaEmprestimoEstaValida(Emprestimo emprestimo){
        if(!emprestimo.getDataPrevistaDevolucaoEmprestimo().isAfter(emprestimo.getDataInicioEmprestimo())){
            throw new LivrariaException(
                    MessageProperties.getMensagemPadrao("livro.data.prevista"));
        }
        return true;
    }

    public boolean dataDevolucaoEmprestimoEstaValida(Emprestimo emprestimo){
        if(Objects.nonNull(emprestimo.getDataDevolucaoEmprestimo())){
            if(!emprestimo.getDataDevolucaoEmprestimo().isAfter(emprestimo.getDataInicioEmprestimo()))
                throw new LivrariaException(
                        MessageProperties.getMensagemPadrao("livro.data.devolucao"));
        }
        return true;
    }

    public boolean limiteQuantidadeDeLivrosNaoUltrapassada(Emprestimo emprestimo){
        int limiteLivrosPorEmprestimo = 3;
        return emprestimo.getLivros().size() <= limiteLivrosPorEmprestimo;
    }

    public List<Emprestimo> consultaEmprestimosPorUsuario(){
        return null;
    }

    private double calculaValorEmprestimo(Emprestimo emprestimo){
        double valorFixo = 5;
        double valorMultaDiaria = 0.4;
        double limitePorcentagemMulta = 0.6;

        if(emprestimo.getDataDevolucaoEmprestimo().isAfter(emprestimo.getDataPrevistaDevolucaoEmprestimo())){
            double somaMultaDiaria = valorMultaDiaria * emprestimo.getDataDevolucaoEmprestimo().compareTo(emprestimo.getDataPrevistaDevolucaoEmprestimo());
            return somaMultaDiaria > (valorFixo * emprestimo.getLivros().size()) * limitePorcentagemMulta ? (valorFixo * emprestimo.getLivros().size()) + (valorFixo * emprestimo.getLivros().size()) * limitePorcentagemMulta : (valorFixo * emprestimo.getLivros().size()) + somaMultaDiaria;
        }else{
            return valorFixo * emprestimo.getLivros().size();
        }
    }

}
