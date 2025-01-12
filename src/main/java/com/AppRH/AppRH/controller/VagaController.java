package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.model.Candidato;
import com.AppRH.AppRH.model.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class VagaController {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
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
    @RequestMapping("/vagas")
    public ModelAndView listVagas() {
        ModelAndView modelAndView = new ModelAndView("vaga/listaVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        modelAndView.addObject("vagas", vagas);
        return modelAndView;
    }

    @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("vaga/detalhesVaga");
        modelAndView.addObject("vaga", vaga);
        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        modelAndView.addObject("candidatos", candidatos);
        return modelAndView;
    }


    //DELETA VAGA
    @RequestMapping("/deletarVaga")
    public String deletarVaga(long codigo) {
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

    //ADICIONA CANDIDATO
    @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("codigo") long codigo, @Valid Candidato candidato,
                                   BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/{codigo}";
        }

        // rg duplicado
        if (candidatoRepository.findByRg(candidato.getRg()) != null) {
            attributes.addFlashAttribute("mensagem_erro", "RG duplicado");
            return "redirect:/{codigo}";
        }

        Vaga vaga = vagaRepository.findByCodigo(codigo);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato adionado com sucesso!");
        return "redirect:/{codigo}";
    }

    // DELETA CANDIDATO PELO RG
    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg){
        Candidato candidato = candidatoRepository.findByRg(rg);
        Vaga vaga = candidato.getVaga();
        String codigo = "" + vaga.getCodigo();

        candidatoRepository.delete(candidato);
        return "redirect:/" + codigo;
    }

    //MÉTODOS QUE ATUALIZAM VAGA
    //formulário edição de vaga

    @RequestMapping(value = "/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editarVaga(long codigo){
        Vaga vaga = vagaRepository.findByCodigo(codigo);
        ModelAndView modelAndView = new ModelAndView("vaga/update-vaga");
        modelAndView.addObject("vaga",vaga);
        return modelAndView;
    }

    //UPDATE VAGA
    @RequestMapping(value = "/editar-vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes){
         vagaRepository.save(vaga);
         attributes.addFlashAttribute("sucess","vaga alterada com sucesso!");

         long codigoLong = vaga.getCodigo();
         String codigo = "" + codigoLong;
         return "redirect:/" + codigo;
    }
}
