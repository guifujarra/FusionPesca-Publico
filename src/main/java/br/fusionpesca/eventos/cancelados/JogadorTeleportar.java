package br.fusionpesca.eventos.cancelados;

import br.fusionpesca.Main;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.servicos.ServicoPesca;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class JogadorTeleportar implements Listener, EventoCancelado {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void aoTeleportar(PlayerTeleportEvent e) {
        Player p = e.getPlayer();
        if (Gerenciamento.isPescador(p)) {
            if (e.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND) {
                return;
            }
            Bukkit.getScheduler().runTaskLater(Main.plugin, () -> {
                p.teleport((Location) Main.plugin.configLoc.get(".Entrada"), PlayerTeleportEvent.TeleportCause.COMMAND);
            }, 2L);
            cancelado(p);
        }
    }


}
