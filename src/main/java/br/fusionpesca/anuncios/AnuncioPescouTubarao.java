package br.fusionpesca.anuncios;

import org.bukkit.ChatColor;

public class AnuncioPescouTubarao extends AnuncioPesca{

    private static final String MENSAGEM = ChatColor.YELLOW + "pescou um " + ChatColor.LIGHT_PURPLE + "TUBARAO!" ;

    public AnuncioPescouTubarao() {
        super(MENSAGEM);
    }
}
