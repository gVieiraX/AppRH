package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.model.Dependentes;
import com.AppRH.AppRH.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DependentesRepository extends JpaRepository<Dependentes, String> {
    Iterable<Dependentes> findByFuncionarios(Funcionarios funcionarios);
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);
    List<Dependentes> findByNome(String nome);
}
