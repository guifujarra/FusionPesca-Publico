package br.fusionpesca.servicos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RemoverPescador implements ServicoPesca{


    @Override
    public void executar(Player p) {
        new PagarJogador().executar(p);
        GerenciamentoPagamento.removerJogadorPagamento(Gerenciamento.getPescador(p));
        Gerenciamento.removePescador(p);
        p.teleport(SAIDA);
        p.getInventory().clear();
        p.sendMessage(ChatColor.RED + "Você saiu da pesca.");
    }
}
