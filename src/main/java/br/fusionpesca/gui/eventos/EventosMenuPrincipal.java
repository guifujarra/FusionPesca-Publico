package br.fusionpesca.gui.eventos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gui.MenuLojaVaras;
import br.fusionpesca.gui.MenuMinhasVaras;
import br.fusionpesca.gui.MenuPrincipal;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public class EventosMenuPrincipal extends EventoMenu implements Listener {


    @EventHandler
    public void aoClicarMenu(InventoryClickEvent e){
        if(e.getInventory().getTitle().equals(MenuPrincipal.TITULO)){
            ItemStack item = e.getCurrentItem();
            e.setCancelled(true);
            if(!isValido(item, e.getRawSlot())){
                return;
            }
            Player p = (Player) e.getWhoClicked();
            if(item.getType() == Material.SKULL || item.getType() == Material.SKULL_ITEM){
                Pescador pescador = Gerenciamento.getPescador(p);
                pescador.setMenuMinhasVaras(new MenuMinhasVaras(p));
            }
            if(item.equals(MenuPrincipal.LOJA_VARAS)){
                new MenuLojaVaras(p);
            }

        }
    }
}
