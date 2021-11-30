package br.fusionpesca.servicos;


import br.fusionpesca.Main;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import br.fusionpesca.gerenciamento.GerenciamentoVaras;
import br.fusionpesca.gui.MenuLojaVaras;
import br.fusionpesca.pescador.Pescador;
import br.fusionpesca.vara.Vara;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ComprarNovaVara implements ServicoPesca {
    private Integer slot;

    public ComprarNovaVara(Integer slot) {
        this.slot = slot;
    }

    @Override
    public void executar(Player p) {
        Pescador pescador = Gerenciamento.getPescador(p);
        Vara varaCompra = MenuLojaVaras.getVaraBySlot(slot);
        if (pescador.getVaras().contains(varaCompra.getNome())) {
            p.closeInventory();
            p.sendMessage(ChatColor.RED + "Você já possui esta vara!");
            somErro(p);
            return;
        }
        p.closeInventory();
        p.sendMessage(ChatColor.GREEN + "Processando transação...");
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
            if(!GerenciamentoPagamento.econ.withdrawPlayer(p, varaCompra.getCusto()).transactionSuccess()){
                p.sendMessage(ChatColor.RED + "Você não tem dinheiro para comprar esta vara!");
                return;
            }
            Main.plugin.bd.adicionarVara(pescador, varaCompra.getNome());
            pescador.addVara(varaCompra.getNome());
            Bukkit.getScheduler().runTask(Main.plugin, () -> {
                p.sendMessage(ChatColor.GREEN + "Vara adquirida com sucessso! Custo: " + varaCompra.getCusto());
                somFecharMenu(p);
            });
        });

    }
}
