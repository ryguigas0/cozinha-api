package com.espm.guilherme.cozinhaapi.itemMenuIngrediente;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_menu_ingrediente")
public class ItemMenuIngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "item_menu_id")
    private int itemMenuId;

    @Column(name = "ingrediente_id")
    private int ingredienteId;

    @Column(name = "quantidade_ingrediente")
    private int quantidadeIngrediente;

    public ItemMenuIngredienteModel() {
    }

    public ItemMenuIngredienteModel(int itemMenuId, int ingredienteId, int quantidadeIngrediente) {
        this.itemMenuId = itemMenuId;
        this.ingredienteId = ingredienteId;
        this.quantidadeIngrediente = quantidadeIngrediente;
    }

    public ItemMenuIngredienteModel(ItemMenuIngredienteRequestTO to) {
        this.itemMenuId = to.itemMenuId();
        this.ingredienteId = to.ingredienteId();
        this.quantidadeIngrediente = to.quantidadeIngrediente();
    }
    

    public int getId() {
        return id;
    }

    public int getItemMenuId() {
        return itemMenuId;
    }

    public int getIngredienteId() {
        return ingredienteId;
    }

    public int getQuantidadeIngrediente() {
        return quantidadeIngrediente;
    }

}
