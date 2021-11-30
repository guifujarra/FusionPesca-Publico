package br.fusionpesca.encantamentos.eventos;

import br.fusionpesca.Main;
import br.fusionpesca.eventos.customizados.JogadorPescarEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class LucroEvento implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void aoPescar(JogadorPescarEvent e){
        Player p = e.getPlayer();
        ItemStack vara = e.getVara();
        int level = vara.getEnchantmentLevel(Main.plugin.lucro);
        if(level < 1){
            return;
        }

        double bonus = ((double)1 / (level + 2)) + (double)(level+1)/2;
        double novoValor = bonus * e.getValorPesca();
        double valorBonus = novoValor - e.getValorPesca();
        e.setValorPesca(novoValor);
        p.sendMessage(ChatColor.GREEN + "Você receberá um bônus de " + Math.round(valorBonus) + " coins graças ao encantamento Lucro!");

    }
}
