package br.fusionpesca.eventos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.servicos.RemoverPescador;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JogadorSair implements Listener {


    @EventHandler
    public void aoJogadorSair(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(Gerenciamento.isPescador(p)){
            new RemoverPescador().executar(p);
        }
    }

    @EventHandler
    public void aoJogadorExpulso(PlayerKickEvent e){
        Player p = e.getPlayer();
        if(Gerenciamento.isPescador(p)){
            new RemoverPescador().executar(p);
        }
    }
}
