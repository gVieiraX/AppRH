package com.AppRH.AppRH.controller;

import com.AppRH.AppRH.repository.DependentesRepository;
import com.AppRH.AppRH.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FuncionariosController {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    @Autowired
    private DependentesRepository dependentesRepository;
}
