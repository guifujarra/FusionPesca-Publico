package br.fusionpesca.gui;

import br.fusionpesca.gerenciamento.Gerenciamento;
import br.fusionpesca.gerenciamento.GerenciamentoVaras;
import br.fusionpesca.pescador.Pescador;
import br.fusionpesca.vara.VaraComparator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuMinhasVaras extends MenuPesca {

    public static final String TITULO = ChatColor.BLUE + "Pesca - Minhas varas";
    public static final int LINHAS = 3;

    private HashMap<Integer, String> slotVara = new HashMap<>();

    public MenuMinhasVaras(Player p) {
        super(TITULO, LINHAS);
        Pescador pescador = Gerenciamento.getPescador(p);
        montarInventario(pescador);
        super.abrirMenu(p);
    }

    public void montarInventario(Pescador p) {
        List<ItemStack> varas = new ArrayList<>();
        GerenciamentoVaras gerenciamentoVaras = new GerenciamentoVaras();
        p.getVaras().forEach(nomeVara -> {
            slotVara.put(slotVara.size(), nomeVara);
            ItemStack vara = gerenciamentoVaras.getVara(nomeVara).getItem();
            varas.add(itemSelecao(p, nomeVara, vara));
        });
        varas.forEach(vara -> inventario.addItem(vara));
    }

    public String getVaraBySlot(Integer slot) {
        return slotVara.get(slot);
    }

    private ItemStack itemSelecao(Pescador p, String nomeVara, ItemStack item) {
        if (p.getVaraAtual().equals(nomeVara)) {
            ItemStack vara = item.clone();
            ItemMeta varaMeta = vara.getItemMeta();
            List<String> lore = varaMeta.getLore();
            lore.add("");
            lore.add(ChatColor.RED + "Equipada");
            varaMeta.setLore(lore);
            vara.setItemMeta(varaMeta);
            return vara;
        }
        return item;
    }
}
