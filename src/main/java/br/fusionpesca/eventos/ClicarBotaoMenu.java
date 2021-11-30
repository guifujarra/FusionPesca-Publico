package br.fusionpesca.eventos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gui.MenuPrincipal;
import br.fusionpesca.servicos.AtualizarInventario;
import br.fusionpesca.servicos.IniciarMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ClicarBotaoMenu implements Listener {


    @EventHandler
    public void aoClicarMenu(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!Gerenciamento.isPescador(p)){
            return;
        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            ItemStack item = e.getItem();
            if(item.equals(AtualizarInventario.MENU)){
                new IniciarMenu().executar(p);
            }
        }
    }
}
