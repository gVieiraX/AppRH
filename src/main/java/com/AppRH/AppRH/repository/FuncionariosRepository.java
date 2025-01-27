package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionariosRepository extends JpaRepository<Funcionarios,String> {
    Funcionarios findById(long id);
    Funcionarios findByNome(String nome);

    @Query(value = "select u from Funcionarios u where u.nome like %?1%")
    List<Funcionarios> findByNomes(String nome);
}