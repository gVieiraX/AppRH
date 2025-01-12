package com.AppRH.AppRH.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "depedentes")
public class Dependentes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "cpf_dependentes")
    private String cpf;

    @Column(nullable = false, name = "nome_dependentes")
    private String nome;

    @Column(nullable = false, name = "dataNascimento_dependentes")
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "funcionarios_id", nullable = false)
    private Funcionarios funcionarios;
}
