package br.fusionpesca.servicos;

import br.fusionpesca.Main;
import br.fusionpesca.gerenciamento.Gerenciamento;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;


public class Comando implements ServicoPesca {

    private String[] args;

    public Comando(String[] args) {
        this.args = args;
    }


    @Override
    public void executar(Player p) {
        if (args.length >= 1) {
            if (p.hasPermission("fusion.pesca.staff")) {
                Location loc = p.getLocation();
                YamlConfiguration configLoc = Main.plugin.configLoc;
                switch (args[0]) {
                    case "setentrada" -> {
                        configLoc.set(".Entrada", loc);
                        salvarLocations();
                        p.sendMessage(ChatColor.GREEN + "Entrada definida");
                        somSucesso(p);
                        return;
                    }
                    case "setsaida" -> {
                        configLoc.set(".Saida", loc);
                        salvarLocations();
                        p.sendMessage(ChatColor.GREEN + "Saida definida");
                        somSucesso(p);
                        return;
                    }
                }
            }
        }
        if (Gerenciamento.isPescador(p)) {
            new RemoverPescador().executar(p);
            return;
        }
        new AdicionarPescador().executar(p);

    }


    private void salvarLocations() {
        try {
            Main.plugin.configLoc.save(Main.plugin.configFileLoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
