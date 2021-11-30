package br.fusionpesca.servicos;

import br.fusionpesca.Main;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AlterarVaraAtual implements ServicoPesca {

    private Integer slot;

    public AlterarVaraAtual(Integer slot) {
        this.slot = slot;
    }


    @Override
    public void executar(Player p) {
        Pescador pescador = Gerenciamento.getPescador(p);
        String nomeVara = pescador.getMenuMinhasVaras().getVaraBySlot(slot);
        if(pescador.getVaraAtual().equals(nomeVara)){
            p.closeInventory();
            somErro(p);
            p.sendMessage(ChatColor.RED + "Esta vara já está equipada.");
            return;
        }
        pescador.setVaraAtual(nomeVara);
        p.closeInventory();
        Bukkit.getScheduler().runTaskAsynchronously(Main.plugin, () -> {
            Main.plugin.bd.alterarVaraAtual(pescador, nomeVara);
            Bukkit.getScheduler().runTask(Main.plugin, () -> {
                new AtualizarInventario().executar(p);
                p.sendMessage(ChatColor.GREEN + "Você alterou sua vara com sucesso!");
                somFecharMenu(p);
                p.closeInventory();
            });

        });
    }
}
