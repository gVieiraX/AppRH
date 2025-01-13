package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.model.Funcionarios;
import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionariosRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
