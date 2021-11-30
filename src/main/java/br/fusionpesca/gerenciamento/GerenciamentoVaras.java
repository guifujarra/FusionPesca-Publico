package br.fusionpesca.gerenciamento;

import br.fusionpesca.Main;
import br.fusionpesca.utils.IntegerToRoman;
import br.fusionpesca.vara.Vara;
import br.fusionpesca.vara.VaraComparator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GerenciamentoVaras {

    private static Map<String, Vara> varas = new HashMap<>();

    static {
        carregarVaras();
    }


    public Vara getVara(String nomeVara) {
        return varas.get(nomeVara);
    }

    public List<Vara> getVaras() {
        List<Vara> lista = new ArrayList<>();
        varas.values().forEach(lista::add);
        lista.sort(new VaraComparator());
        return lista;
    }

    public static void carregarVaras() {
        Map<String, Object> sessoes = Main.plugin.configVaras.getConfigurationSection("varas").getValues(false);
        for (String chave : sessoes.keySet()) {
            criarVara(chave);
        }
    }

    private static void criarVara(String nomeVara) {
        String dir = "varas." + nomeVara + ".";
        ItemStack item = new ItemStack(Material.FISHING_ROD);
        adicionarEncantamentos(item, dir);
        adicionarItemMeta(item, dir);
        Vara vara = new Vara(nomeVara, item, getCustoVara(dir));
        vara.setId(varas.size());
        varas.put(nomeVara, vara);
    }

    private static void adicionarEncantamentos(ItemStack item, String dir) {
        ItemMeta itemMeta = item.getItemMeta();

        HashMap<Enchantment, Integer> encantamentos = new HashMap<>();
        List<String> loreEncantamentos = new ArrayList<>();

        for (String encString : Main.plugin.configVaras.getStringList(dir + ".encantamentos")) {
            Enchantment encantamento = Enchantment.getByName(encString.split(":")[0]);
            int level = Integer.parseInt(encString.split(":")[1]);
            encantamentos.put(encantamento, level);
            if(Main.plugin.encantamentos.contains(encantamento)){
                loreEncantamentos.add(ChatColor.GRAY + encantamento.getName() + " " + IntegerToRoman.toRoman(level));
            }
        }
        itemMeta.setLore(loreEncantamentos);
        item.setItemMeta(itemMeta);
        item.addEnchantments(encantamentos);
    }

    private static void adicionarItemMeta(ItemStack item, String dir) {
        ItemMeta itemMeta = item.getItemMeta();
        String nome = Main.plugin.configVaras.getString(dir + ".nome");
        List<String> lore;
        if (!itemMeta.hasLore()) {
            lore = Main.plugin.configVaras.getStringList(dir + ".lore");
        }else{
            lore = itemMeta.getLore();
            lore.add("");
            lore.addAll(Main.plugin.configVaras.getStringList(dir + ".lore"));
        }
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nome));
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    private static double getCustoVara(String dir) {
        return Main.plugin.configVaras.getDouble(dir + ".custo");
    }
}
