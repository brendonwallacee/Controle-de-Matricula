package com.pi.model;

import com.pi.dto.UsuarioRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;

    @Column(name = "nomeUsuario")
    private String nomeUsuario;

    @Column(name = "senha")
    private String senha;

    public static Usuario of(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(usuarioRequest.getNomeUsuario());
        usuario.setSenha(usuarioRequest.getSenha());
        return usuario;
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
