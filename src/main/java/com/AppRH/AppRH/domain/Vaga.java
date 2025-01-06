package com.AppRH.AppRH.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vaga")
public class Vaga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ajustado para MySQL
    private long id;

    @Column(nullable = false, name = "nome_vaga")
    private String nome;

    @Column(nullable = false, name = "descricao_vaga")
    private String descricao;

    @Column(nullable = false, name = "data_publicacao")
    private LocalDate data;

    @Column(nullable = false, name = "salario_vaga")
    private BigDecimal salario;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
    private List<Candidato> candidatos;

}
