package br.fusionpesca.eventos.cancelados;

import br.fusionpesca.gerenciamento.Gerenciamento;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DroparItem implements Listener, EventoCancelado {


    @EventHandler
    public void aoDropar(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(Gerenciamento.isPescador(p)){
            e.setCancelled(true);
            cancelado(p);
        }
    }
}
