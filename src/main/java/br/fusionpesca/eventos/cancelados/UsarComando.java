package br.fusionpesca.eventos.cancelados;

import br.fusionpesca.gerenciamento.Gerenciamento;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


public class UsarComando implements Listener, EventoCancelado {


    @EventHandler
    public void aoUsarComando(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (Gerenciamento.isPescador(p)) {
            String comando = e.getMessage().split(" ")[0].toLowerCase();
            if (!Gerenciamento.comandos.contains(comando)) {
                e.setCancelled(true);
                cancelado(p);
            }
        }
    }
}
