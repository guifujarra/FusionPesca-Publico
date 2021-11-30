package br.fusionpesca.gui;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoVaras;
import br.fusionpesca.pescador.Pescador;
import br.fusionpesca.vara.Vara;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuLojaVaras extends MenuPesca {


    public static final String TITULO = ChatColor.BLUE + "Pesca - Loja de varas";
    public static final int LINHAS = 3;


    private static final HashMap<Integer, String> slotVara = new HashMap<>();
    static{
        new GerenciamentoVaras().getVaras().forEach(vara -> {

            slotVara.put(slotVara.size(), vara.getNome());
        });
    }

    public MenuLojaVaras(Player p) {
        super(TITULO, LINHAS);
        Pescador pescador = Gerenciamento.getPescador(p);
        montarInventario(pescador);
        super.abrirMenu(p);
    }

    private void montarInventario(Pescador pescador){
        GerenciamentoVaras gerenciamentoVaras = new GerenciamentoVaras();
        gerenciamentoVaras.getVaras().forEach(vara ->{
            ItemStack item = vara.getItem().clone();
            adicionarLore(pescador, item, vara);
            inventario.addItem(item);
        });
    }

    private static void adicionarLore(Pescador pescador, ItemStack item, Vara vara){
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore;
        if(itemMeta.hasLore()) {
            lore = itemMeta.getLore();
        }else{
            lore = new ArrayList<>();
        }
        lore.add("");
        lore.add(ChatColor.YELLOW + "Custo: " + vara.getCusto() + " coins");
        lore.add("");
        if(pescador.getVaras().contains(vara.getNome())){
            lore.add(ChatColor.RED + "Já possui");
        }else{
            lore.add(ChatColor.GREEN + "Clique para comprar");
        }
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    public static Vara getVaraBySlot(Integer slot){
        return new GerenciamentoVaras().getVara(slotVara.get(slot));
    }

}
