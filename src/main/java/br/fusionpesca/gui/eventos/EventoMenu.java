package br.fusionpesca.gui.eventos;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class EventoMenu {


    protected boolean isValido(ItemStack item, int slot){
        if(item == null || item.getType() == Material.AIR){
            return false;
        }
        return slot < 27;
    }
}
