package com.AppRH.AppRH.repository;


import com.AppRH.AppRH.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga,String> {
    Vaga findByCodigo(long codigo);
    List<Vaga> findByNome(String nome);
}

