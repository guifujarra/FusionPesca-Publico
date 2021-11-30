package br.fusionpesca.anuncios;

import br.fusionpesca.gerenciamento.Gerenciamento;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public abstract class AnuncioPesca {

    private final String MENSAGEM;
    private static final Sound SOM = Sound.FIREWORK_LARGE_BLAST2;


    protected AnuncioPesca(String mensagem) {
        MENSAGEM = mensagem;
    }


    public void anunciar(Player p) {
        p.sendMessage(ChatColor.GREEN + "PARABÉNS! Você " + MENSAGEM);
        String anuncio = ChatColor.YELLOW + p.getName() + " " + MENSAGEM;
        Gerenciamento.getPescadores().forEach((jogador, pescador) -> {
            jogador.sendMessage(anuncio);
            jogador.playSound(jogador.getLocation(), SOM, 1 ,1);
        });
    }
}
