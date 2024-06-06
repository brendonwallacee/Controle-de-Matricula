package com.pi.service;

import com.pi.dto.UsuarioRequest;
import com.pi.exceptions.exception.ValidacaoException;
import com.pi.model.Usuario;
import com.pi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registrar(UsuarioRequest request) {
        var senhaCriptografada = criptografarSenha(request.getSenha());

        request.setSenha(senhaCriptografada);

        this.usuarioRepository.save(Usuario.of(request));
    }

    public boolean login(UsuarioRequest request) {
        var usuario = usuarioRepository.findByNomeUsuario(request.getNomeUsuario())
                .orElseThrow(() -> new ValidacaoException("Usuario não encontrado"));

        return descriptografarSenha(request.getSenha(), usuario.getSenha());
    }

    private String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    private boolean descriptografarSenha(String senha, String senhaDoUsuarioSalvo) {
        return passwordEncoder.matches(senha, senhaDoUsuarioSalvo);
    }


}
