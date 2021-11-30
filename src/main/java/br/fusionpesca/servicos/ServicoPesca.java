package br.fusionpesca.servicos;

import br.fusionpesca.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public interface ServicoPesca {

    Location ENTRADA = (Location) Main.plugin.configLoc.get(".Entrada");
    Location SAIDA = (Location) Main.plugin.configLoc.get(".Saida");

    Sound ABRIR_MENU = Sound.CHEST_OPEN;
    Sound FECHAR_MENU = Sound.CHEST_CLOSE;
    Sound SUCESSO = Sound.WATER;
    Sound ERRO = Sound.VILLAGER_NO;

    void executar(Player p);


    default void somErro(Player p) {
        p.playSound(p.getLocation(), ERRO, 1, 1);
    }

    default void somSucesso(Player p) {
        p.playSound(p.getLocation(), SUCESSO, 1, 1);
    }

    default void somAbrirMenu(Player p){
        p.playSound(p.getLocation(), ABRIR_MENU, 1, 1);
    }
    default void somFecharMenu(Player p){
        p.playSound(p.getLocation(), FECHAR_MENU, 1, 1);
    }
}
