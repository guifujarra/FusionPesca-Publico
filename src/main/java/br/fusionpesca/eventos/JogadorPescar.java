package br.fusionpesca.eventos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.recompensas.controle.ControladorDeRecompensas;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fish;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;


public class JogadorPescar implements Listener {


    @EventHandler
    public void aoPescar(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if (!Gerenciamento.isPescador(p)) {
            return;
        }
        if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH || e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY){
            ControladorDeRecompensas.ProcessarRecompensa(p, e);

        }
    }
}
