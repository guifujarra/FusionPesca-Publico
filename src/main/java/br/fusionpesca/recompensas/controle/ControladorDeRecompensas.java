package br.fusionpesca.recompensas.controle;

import br.fusionpesca.eventos.customizados.JogadorPescarEvent;
import br.fusionpesca.recompensas.Peixe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ControladorDeRecompensas {

    private static Random random = new Random();


    public static void ProcessarRecompensa(Player p, PlayerFishEvent e){
        e.getCaught().remove();

        int recompensaChance = random.nextInt(100);
        Peixe peixe;
        if(recompensaChance <= 59){
            peixe = Peixe.SARDINHA;
        }else if(recompensaChance < 89){
            peixe = Peixe.SALMAO;
        }else if(recompensaChance < 98){
            peixe = Peixe.PEIXE_ESPADA;
        }else{
            peixe = Peixe.TUBARAO;

        }


        ItemStack vara = p.getItemInHand();
        JogadorPescarEvent jogadorPescarEvent = new JogadorPescarEvent(p, peixe, vara);
        Bukkit.getPluginManager().callEvent(jogadorPescarEvent);




    }

}
