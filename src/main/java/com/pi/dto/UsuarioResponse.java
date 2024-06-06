package com.pi.dto;

import com.pi.model.Usuario;

public class UsuarioResponse {
    
    private int id;
    private String nomeUsuario;
    private String senha;

    public static UsuarioResponse of(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setNomeUsuario(usuario.getNomeUsuario());
        usuarioResponse.setSenha(usuario.getSenha());
        return usuarioResponse;
    }

    public UsuarioResponse() {
    }

    public UsuarioResponse(int id, String nomeUsuario, String senha) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
