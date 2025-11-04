package com.taskmng.br.TaskMNG.controllers;

import com.taskmng.br.TaskMNG.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskmng/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
}
