package com.AppRH.AppRH.repository;

import com.AppRH.AppRH.model.Candidato;
import com.AppRH.AppRH.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CandidatoRepository extends JpaRepository<Candidato,String> {
    Iterable<Candidato>findByVaga(Vaga vaga);

    Candidato findByRg(String rg);
    Candidato findById(long id);
    List<Candidato>findByNomeCandidato(String nomeCandidato);

    @Query(value = "select u from Candidato u where u.nomeCandidato like %?1%")
    List<Candidato> findByNomesCandidatos(String nome);

}