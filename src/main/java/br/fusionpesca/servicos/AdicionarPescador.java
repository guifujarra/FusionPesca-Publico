package br.fusionpesca.servicos;

import br.fusionpesca.Main;
import br.fusionpesca.bd.BD;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AdicionarPescador implements ServicoPesca {


    @Override
    public void executar(Player p) {
        if (!invIsVazio(p)) {
            p.sendMessage(ChatColor.RED + "Você precisa esvaziar seu inventário para entrar na pesca!");
            somErro(p);
            return;
        }
        for (ItemStack armadura : p.getInventory().getArmorContents()) {
            if (armadura.getType() != Material.AIR) {
                somErro(p);
                p.sendMessage(ChatColor.RED + "Você precisa remover sua armadura para entrar na pesca!");
                return;
            }
        }

        inserirJogador(p);
    }

    private void inserirJogador(Player p) {
        p.sendMessage(ChatColor.GREEN + "Entrando na pesca...");
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
            Pescador pescador = Main.plugin.bd.recuperarPescador(p);
            Bukkit.getScheduler().runTask(Main.plugin, () ->{
                p.teleport(ENTRADA);
                new Gerenciamento().addPescador(p, pescador);
                GerenciamentoPagamento.adicionarPagamento(pescador, 0.0);
                new AtualizarInventario().executar(p);
                somSucesso(p);
                p.sendMessage(ChatColor.GREEN + "Você entrou na pesca! Para sair digite /pesca");
                p.sendMessage(ChatColor.YELLOW + "Pesque normalmente sem se preocupar, mesmo que o peixe não vá" +
                        " para o seu inventário ele será contabilizado! Você receberá seu dinheiro ao sair.");
            });
        });

    }

    public boolean invIsVazio(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null) {
                return false;
            }
        }
        return true;
    }
}
