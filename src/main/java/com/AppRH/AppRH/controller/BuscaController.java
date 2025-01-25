package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionariosRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuscaController {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private DependentesRepository dependentesRepository;

    @Autowired
    private CandidatoRepository candidatoRepository;

    //GET
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView abrirIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    //POST
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("buscar")String buscar, @RequestParam("nome")String nome){
        
        ModelAndView modelAndView = new ModelAndView("index");
        String mensagem =  "Resultado da busca por: " + buscar;
        
        if(nome.equals("nomeFuncionario")){
            modelAndView.addObject("funcionarios", funcionariosRepository.findByNomes(buscar));
        } else if (nome.equals("nomedependente")) {
            modelAndView.addObject("dependentes", dependentesRepository.findByNomesDependentes(buscar));
        } else if (nome.equals("nomecandidato")) {
            modelAndView.addObject("candidatos", candidatoRepository.findByNomesCandidatos(buscar));
        } else if (nome.equals("nomeVaga")) {
            modelAndView.addObject("vagas", vagaRepository.findByNomesVagas(buscar));
        } else {
            modelAndView.addObject("funcionarios", funcionariosRepository.findByNomes(buscar));
            modelAndView.addObject("dependentes", dependentesRepository.findByNomesDependentes(buscar));
            modelAndView.addObject("candidatos", candidatoRepository.findByNomesCandidatos(buscar));
            modelAndView.addObject("vagas", vagaRepository.findByNomesVagas(buscar));
        }
        modelAndView.addObject("mensagem",mensagem);
        return modelAndView;
    }
}
