package com.ifma.livraria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_emprestimo")
public class Emprestimo {

    @EmbeddedId
    private EmprestimoPK id = new EmprestimoPK();
    private LocalDateTime dataInicioEmprestimo;
    private LocalDateTime dataPrevistaDevolucaoEmprestimo;
    private LocalDateTime dataDevolucaoEmprestimo;

}
