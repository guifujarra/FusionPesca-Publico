package br.fusionpesca.eventos;

import br.fusionpesca.eventos.customizados.JogadorPescarEvent;
import br.fusionpesca.recompensas.Peixe;
import br.fusionpesca.servicos.PeixePescado;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class JogadorPescarSucesso implements Listener {


    @EventHandler
    public void aoPescarSucesso(JogadorPescarEvent e){
        Player p = e.getPlayer();
        Peixe peixe = e.getPeixe();
        new PeixePescado(peixe, e.getValorPesca()).executar(p);
    }
}
