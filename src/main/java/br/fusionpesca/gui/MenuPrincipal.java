package br.fusionpesca.gui;

import br.fusionpesca.recompensas.Peixe;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MenuPrincipal extends MenuPesca implements Listener {


    public static final String TITULO = ChatColor.BLUE + "Pesca - Menu Principal";
    public static final int LINHAS = 3;

    private static ItemStack item;

    public final ItemStack MINHAS_VARAS;
    public static final ItemStack LOJA_VARAS;
    public static final ItemStack CATALOGO;

    public MenuPrincipal(Player p) {
        super(TITULO, LINHAS);
        MINHAS_VARAS = getCabeca(p);
        montarInventario();
        super.abrirMenu(p);
    }


    static {
        carregar();
        LOJA_VARAS = getLojaVaras();
        CATALOGO = new ItemStack(Material.BOOK);
        carregarCatalogo();
    }

    public void montarInventario() {
        super.inventario.setItem(12, MINHAS_VARAS);
        super.inventario.setItem(14, LOJA_VARAS);
        super.inventario.setItem(4, CATALOGO);
    }

    private static ItemStack getLojaVaras() {
        ItemStack item = new ItemStack(Material.FISHING_ROD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Loja de varas");
        item.setItemMeta(itemMeta);
        return item;
    }

    private ItemStack getCabeca(Player p)  {
        ItemStack cabeca = item.clone();
        SkullMeta skullMeta = (SkullMeta) cabeca.getItemMeta();
        skullMeta.setOwner(p.getName());
        skullMeta.setDisplayName(ChatColor.YELLOW + "Minhas varas");
        cabeca.setItemMeta(skullMeta);
        return cabeca;
    }

    private static void carregar(){
        try {
            item = new ItemStack(Material.SKULL_ITEM);
            item.setDurability((short) 3);
            Class<?> skullMetaClass = null;
            skullMetaClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaSkull");
            Field profileField = skullMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            Base64.Encoder encoder = Base64.getEncoder();
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void carregarCatalogo(){
        ItemMeta itemMeta = CATALOGO.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Catálogo de peixes");
        List<String> lore = new ArrayList<>();
        for(Peixe peixe : Peixe.values()){
            lore.add("");
            lore.add(ChatColor.GREEN + peixe.toString());
            lore.add(ChatColor.YELLOW + "Preço unidade: " + peixe.getValor());
            lore.add(ChatColor.YELLOW + "Chance: " + peixe.getChance() + "%");
        }
        itemMeta.setLore(lore);
        CATALOGO.setItemMeta(itemMeta);
    }


}
