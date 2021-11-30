package br.fusionpesca.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class MenuPesca {

    protected Inventory inventario;

    public MenuPesca(String titulo, int linhas){
        inventario = Bukkit.createInventory(null, 9 * linhas, titulo);
    }


    public void abrirMenu(Player p){
        p.openInventory(inventario);
    }



}
