package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.model.Dependentes;
import com.AppRH.AppRH.model.Funcionarios;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FuncionariosController {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    @Autowired
    private DependentesRepository dependentesRepository;


    //Chama o form de Cadastrar Funcionários
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.GET)
    public String form(){
        return "funcionario/formFuncionario";
    }

    // Cadastrar Funcionários
    @RequestMapping(value = "/cadastrarFuncionario",method = RequestMethod.POST)
    public String form(@Valid Funcionarios funcionarios, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","Verifique os campos");
            return "redirect:/cadastrarFuncionario";
        }
        funcionariosRepository.save(funcionarios);
        attributes.addFlashAttribute("mensagem","Funcionário cadastrado com sucesso!");
        return "redirect:/cadastrarFuncionario";
    }

    //Lista funcionarios
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionarios(){
        ModelAndView modelAndView = new ModelAndView("funcionario/listaFuncionario");
        Iterable<Funcionarios> funcionarios = funcionariosRepository.findAll();
        modelAndView.addObject("funcionarios",funcionarios);
        return  modelAndView;
    }

    //lista funcionarios por id
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
    public ModelAndView dependentes(@PathVariable("id") long id){
        Funcionarios funcionarios = funcionariosRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("funcionario/dependentes");
        modelAndView.addObject("funcionario", funcionarios);

        //Lista de dependentes baseada nos funcionários
        Iterable<Dependentes> dependentes = dependentesRepository.findByFuncionarios(funcionarios);
        modelAndView.addObject("dependentes", dependentes);
        return  modelAndView;

    }

    //Adicionar dependentes
    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.POST)
    public String dependentesPost(@PathVariable("id") long id, Dependentes dependentes, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/dependentes/{id}";
        }

        if (dependentesRepository.findByCpf(dependentes.getCpf()) != null) {
            attributes.addFlashAttribute("mensagem", "CPF duplicado");
            return "redirect:/dependentes/{id}";
        }
        Funcionarios funcionarios = funcionariosRepository.findById(id);
        dependentes.setFuncionarios(funcionarios);
        dependentesRepository.save(dependentes);
        attributes.addFlashAttribute("mensagem","Dependente adicionado com sucesso!");
        return  "redirect:/dependentes/{id}";
    }
    // Deletar funcionario
    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id){
        Funcionarios funcionarios = funcionariosRepository.findById(id);
        funcionariosRepository.delete(funcionarios);
        return "redirect:/funcionarios";
    }

    //Métodos que atualizam funcionário
    //Form
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.GET)
    public ModelAndView editarFuncionario(long id){
        Funcionarios funcionarios = funcionariosRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("funcionario/update-funcionario");
        modelAndView.addObject("funcionario", funcionarios);
        return  modelAndView;
    }

    // update funcionário
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionarios funcionario,  BindingResult result, RedirectAttributes attributes){

        funcionariosRepository.save(funcionario);
        attributes.addFlashAttribute("successs", "Funcionário alterado com sucesso!");

        long idLong = funcionario.getId();
        String id = "" + idLong;
        return "redirect:/dependentes/" + id;

    }
    // Deletar dependente
    @RequestMapping("/deletarDependente")
    public String deletarDependente(String cpf){
        Dependentes dependentes = dependentesRepository.findByCpf(cpf);

        Funcionarios funcionarios = dependentes.getFuncionarios();
        String codigo = "" + funcionarios.getId();

        dependentesRepository.delete(dependentes);
        return "redirect:/dependentes/" + codigo;
    }
}
