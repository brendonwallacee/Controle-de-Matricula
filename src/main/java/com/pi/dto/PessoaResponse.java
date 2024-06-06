package com.pi.dto;

import com.pi.model.Pessoas;

public class PessoaResponse {

    private int id;
    private String nomePessoa;
    private String endereco;
    private String uf;
    private String telefone;
    private String cpf;
    private String email;

    public static PessoaResponse of(Pessoas pessoas) {
        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setId(pessoas.getId());
        pessoaResponse.setNomePessoa(pessoas.getNomePessoa());
        pessoaResponse.setEndereco(pessoas.getEndereco());
        pessoaResponse.setUf(pessoas.getUf());
        pessoaResponse.setTelefone(pessoas.getTelefone());
        pessoaResponse.setCpf(pessoas.getCpf());
        pessoaResponse.setEmail(pessoas.getEmail());
        return pessoaResponse;
    }

    public PessoaResponse() {
    }

    public PessoaResponse(int id, String nomePessoa, String endereco, String uf, String telefone, String cpf, String email) {
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
