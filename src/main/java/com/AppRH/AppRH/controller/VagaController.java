package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.domain.Candidato;
import com.AppRH.AppRH.domain.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    private VagaRepository vagaRepository;

    private CandidatoRepository candidatoRepository;

    //CADASTRA VAGA
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET)
    public String form() {
        return "vaga/formVaga";
    }

    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }

        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    //LISTA VAGAS
    public ModelAndView listVagas() {
        ModelAndView modelAndView = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        modelAndView.addObject("vagas", vagas);
        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("id") long id) {
        Vaga vaga = vagaRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("vaga/detalhesVaga");
        modelAndView.addObject("vaga", vaga);
        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        modelAndView.addObject("candidatos", candidatos);
        return modelAndView;
    }


    //DELETA VAGA
    @RequestMapping("/deletarVaga")
    public String deletarVaga(long id) {
        Vaga vaga = vagaRepository.findById(id);
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

    public String detalhesVagaPost(@PathVariable("id") long id, @Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "verifique os campos");
            return "redirect:/{id}";
        }

        // rg duplicado
        if (candidatoRepository.findByRg(candidato.getRg()) != null) {
            attributes.addFlashAttribute("mensagem erro", "RG DUPLICADO!");
            return "redirect:/{id}";
        }
        Vaga vaga = vagaRepository.findById(id);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensgem","Candidato adicionao com sucesso!");
        return "redirect:/{id}";
    }
    // DELETA CANDIDATO PELO RG
    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg){
        Candidato candidato = candidatoRepository.findByRg(rg);
        Vaga vaga = candidato.getVaga();
        String id = "" + vaga.getId();

        candidatoRepository.delete(candidato);
        return "redirect:/" + id;
    }

    //MÉTODOS QUE ATUALIZAM VAGA
    //formulário edição de vaga

    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editarVaga(long id){
        Vaga vaga = vagaRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("vaga/update-vaga");
        modelAndView.addObject("vaga",vaga);
        return modelAndView;
    }

    //UPDATE VAGA
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
         vagaRepository.save(vaga);
         attributes.addFlashAttribute("sucess","vaga alterada com sucesso!");

         long idLong = vaga.getId();
         String id = "" + idLong;
         return "redirect:/" + id;
    }
}
