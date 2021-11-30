package br.fusionpesca.eventos.cancelados;

import br.fusionpesca.servicos.ServicoPesca;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface EventoCancelado {


    default void cancelado(Player p){
        p.playSound(p.getLocation(), ServicoPesca.ERRO, 1, 1);
        p.sendMessage(ChatColor.RED + "Você não pode fazer isso no mundo da pesca!");
    }
}
