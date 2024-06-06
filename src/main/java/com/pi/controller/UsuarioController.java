package com.pi.controller;

import com.pi.dto.PessoaResponse;
import com.pi.dto.UsuarioRequest;
import com.pi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public void registrarUsuario(@RequestBody UsuarioRequest request) {
        usuarioService.registrar(request);
    }

    @PostMapping("/login")
    public boolean logarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.login(request);
    }
}
