package br.fusionpesca.servicos;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoVaras;
import br.fusionpesca.pescador.Pescador;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class AtualizarInventario implements ServicoPesca {

    public static final ItemStack MENU = new ItemStack(Material.NETHER_STAR);

    static {
        criarItemMenu();
    }


    @Override
    public void executar(Player p) {
        p.getInventory().clear();
        Pescador pescador = Gerenciamento.getPescador(p);
        ItemStack varaPlayer = new GerenciamentoVaras().getVara(pescador.getVaraAtual()).getItem();
        p.getInventory().setItem(0, varaPlayer);
        p.getInventory().setItem(8, MENU);
    }


    public static void criarItemMenu(){
        ItemMeta itemMeta = MENU.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW  + "Menu principal");
        itemMeta.setLore(Arrays.asList("", ChatColor.YELLOW + "Clique direito para abrir"));
        MENU.setItemMeta(itemMeta);

    }


}
