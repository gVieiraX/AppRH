package com.AppRH.AppRH.repository;


import com.AppRH.AppRH.model.Funcionarios;
import com.AppRH.AppRH.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga,String> {
    Vaga findByCodigo(long codigo);
    List<Vaga> findByNome(String nome);

    @Query(value = "select u from u Vaga u.nome like %?1%")
    List<Vaga> findByNomesVagas(String nome);
}

