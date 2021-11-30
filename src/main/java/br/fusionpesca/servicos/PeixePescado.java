package br.fusionpesca.servicos;

import br.fusionpesca.anuncios.AnuncioPescouPeixeEspada;
import br.fusionpesca.anuncios.AnuncioPescouTubarao;
import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoPagamento;
import br.fusionpesca.pescador.Pescador;
import br.fusionpesca.recompensas.Peixe;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PeixePescado implements ServicoPesca{

    private final Peixe PEIXE;
    private final double VALOR;

    public PeixePescado(Peixe peixe, double valor){
        PEIXE = peixe;
        this.VALOR = valor;
    }

    @Override
    public void executar(Player p) {
        if(PEIXE == Peixe.TUBARAO){
            new AnuncioPescouTubarao().anunciar(p);
        }else if(PEIXE == Peixe.PEIXE_ESPADA){
            new AnuncioPescouPeixeEspada().anunciar(p);
        }else{
            p.sendMessage(ChatColor.GREEN + "Você capturou um(a) " + ChatColor.YELLOW + PEIXE);
            p.playSound(p.getLocation(), Sound.SWIM, 1, 1);
        }
        Pescador pescador = Gerenciamento.getPescador(p);
        pescador.addPeixe(PEIXE);
        GerenciamentoPagamento.adicionarPagamento(pescador, VALOR);
    }
}
