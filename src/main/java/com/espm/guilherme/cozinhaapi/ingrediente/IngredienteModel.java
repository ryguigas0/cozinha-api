package com.espm.guilherme.cozinhaapi.ingrediente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")
public class IngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "unidade")
    private String unidade;

    public IngredienteModel() {
    }

    public IngredienteModel(String nome, int quantidade, String unidade) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    public IngredienteModel(IngredienteRequestTO novoIngrediente) {
        this.nome = novoIngrediente.nome();
        this.quantidade = novoIngrediente.quantidade();
        this.unidade = novoIngrediente.unidade();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

}
