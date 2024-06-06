package com.pi.dto;

import com.pi.model.Pessoas;

public class PessoaRequest {

    private int id;
    private String nomePessoa;
    private String endereco;
    private String uf;
    private String telefone;
    private String cpf;
    private String email;

    public PessoaRequest() {
    }

    public PessoaRequest(int id, String nomePessoa, String endereco, String uf, String telefone, String cpf, String email) {
        this.id = id;
        this.nomePessoa = nomePessoa;
        this.endereco = endereco;
        this.uf = uf;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
