package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.model.Candidato;
import com.AppRH.AppRH.model.Dependentes;
import com.AppRH.AppRH.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DependentesRepository extends JpaRepository<Dependentes, String> {
    Iterable<Dependentes> findByFuncionarios(Funcionarios funcionarios);
    Dependentes findByCpf(String cpf);
    Dependentes findById(long id);
    List<Dependentes> findByNome(String nome);

    @Query(value = "select u from Dependentes u where u.nome like %?1%")
    List<Candidato> findByNomesDependentes(String nome);
}
