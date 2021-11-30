package br.fusionpesca.gui.eventos;

import br.fusionpesca.gui.MenuLojaVaras;
import br.fusionpesca.servicos.ComprarNovaVara;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class EventosMenuLojaVaras extends EventoMenu implements Listener {


    @EventHandler
    public void aoClicarMenu(InventoryClickEvent e){
        if(e.getInventory().getTitle().equals(MenuLojaVaras.TITULO)){
            ItemStack item = e.getCurrentItem();
            e.setCancelled(true);
            if(!isValido(item, e.getRawSlot())){
                return;
            }
            Player p = (Player) e.getWhoClicked();
            Integer slot = e.getSlot();
            new ComprarNovaVara(slot).executar(p);

        }
    }

}
