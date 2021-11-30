package br.fusionpesca.anuncios;

import org.bukkit.ChatColor;

public class AnuncioMilagre extends AnuncioPesca{


    private static final String MENSAGEM = ChatColor.RED + "MILAGROSAMENTE"+ ChatColor.YELLOW + " teve seus peixes multiplicados";

    public AnuncioMilagre() {
        super(MENSAGEM);
    }
}
