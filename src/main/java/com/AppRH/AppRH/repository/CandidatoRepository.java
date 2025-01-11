package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.domain.Candidato;
import com.AppRH.AppRH.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato,String> {
    Iterable<Candidato>findByVaga(Vaga vaga);

    Candidato findByRg(String rg);
    Candidato findById(long id);
    List<Candidato>findByNomeCandidato(String nomeCandidato);
}