package br.fusionpesca.encantamentos.eventos;

import br.fusionpesca.Main;
import br.fusionpesca.anuncios.AnuncioMilagre;
import br.fusionpesca.eventos.customizados.JogadorPescarEvent;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MilagreEvento implements Listener {

    private static Random random = new Random();

    @EventHandler
    public void aoPescar(JogadorPescarEvent e) {
        ItemStack vara = e.getVara();
        int level = vara.getEnchantmentLevel(Main.plugin.milagre);
        if (level < 1) {
            return;
        }
        Player p = e.getPlayer();
        Pescador pescador = Gerenciamento.getPescador(p);
        int sorte = random.nextInt(1000);
        if (sorte <= level - 1) {
            new AnuncioMilagre().anunciar(p);
            double valorAntigo = GerenciamentoPagamento.getPagamentoJogador(pescador);
            double novoValor = ((double) level / 10 + 1) * valorAntigo;
            double bonus = novoValor - valorAntigo;
            p.sendMessage(ChatColor.GREEN + "Você receberá " + Math.round(bonus) + " coins a mais graças ao milagre!");
            GerenciamentoPagamento.definirNovoPagamento(pescador, novoValor);
        }
    }
}
