package com.pi.dto;


public class UsuarioRequest {

    private String nomeUsuario;
    private String senha;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String senha, String nomeUsuario) {
        this.senha = senha;
        this.nomeUsuario = nomeUsuario;
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
