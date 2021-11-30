package br.fusionpesca.comandos;

import br.fusionpesca.servicos.Comando;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Pesca implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("pesca")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                new Comando(args).executar(p);
                return true;
            }
            sender.sendMessage(ChatColor.RED + "Comando apenas para jogadores");

        }
        return false;
    }
}
