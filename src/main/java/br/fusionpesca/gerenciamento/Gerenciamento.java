package br.fusionpesca.gerenciamento;

import br.fusionpesca.Main;
import br.fusionpesca.comandos.Pesca;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.entity.Player;

import java.util.*;

public class Gerenciamento {

    private static Map<Player, Pescador> pescadores = new HashMap<>();
    public static List<String> comandos;

    static {
        comandos = Main.plugin.getConfig().getStringList(".Comandos");
    }

    public void addPescador(Player p, Pescador pescador) {
        pescadores.put(p, pescador);
    }

    public static void removePescador(Player p){
        pescadores.remove(p);
    }

    public static boolean isPescador(Player p){
        return pescadores.containsKey(p);
    }

    public static Pescador getPescador(Player p){
        return pescadores.get(p);
    }

    public static Map<Player, Pescador> getPescadores(){
        return pescadores;
    }

}
