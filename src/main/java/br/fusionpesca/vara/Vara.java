package br.fusionpesca.vara;

import org.bukkit.inventory.ItemStack;

public class Vara {

    private int id;
    private String nome;
    private ItemStack item;
    private double custo;

    public Vara(String nome, ItemStack item, double custo) {
        this.nome = nome;
        this.item = item;
        this.custo = custo;
    }

    public ItemStack getItem() {
        return item;
    }

    public double getCusto() {
        return custo;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
