package com.AppRH.AppRH.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "nome_funcionarios")
    private String nome;

    @Column(nullable = false, name = "cargo_funcionarios")
    private String cargo;

    @Column(nullable = false, name = "data_funcionarios")
    private LocalDate data;

    @Column(unique = true, nullable = false, name = "email_funcionarios")
    private String email;

    @OneToMany(mappedBy = "funcionarios", cascade = CascadeType.REMOVE)
    private List<Dependentes> dependentesList;

}
