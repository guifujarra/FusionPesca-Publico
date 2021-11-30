package br.fusionpesca.anuncios;

import org.bukkit.ChatColor;

public class AnuncioPescouPeixeEspada extends AnuncioPesca{

    private static final String MENSAGEM = ChatColor.YELLOW + "pescou um " + ChatColor.DARK_PURPLE + "PEIXE-ESPADA!" ;

    public AnuncioPescouPeixeEspada() {
        super(MENSAGEM);
    }
}
