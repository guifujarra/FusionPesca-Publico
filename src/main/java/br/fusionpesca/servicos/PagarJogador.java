package br.fusionpesca.servicos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PagarJogador implements ServicoPesca {
    @Override
    public void executar(Player p) {
        Pescador pescador = Gerenciamento.getPescador(p);
        double valor = GerenciamentoPagamento.getPagamentoJogador(pescador);
        GerenciamentoPagamento.econ.depositPlayer(p, valor);
        p.sendMessage(ChatColor.GREEN + "Você recebeu um pagamento de " + Math.round(valor));
    }
}
