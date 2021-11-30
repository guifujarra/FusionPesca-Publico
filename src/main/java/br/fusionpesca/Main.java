package br.fusionpesca;

import br.fusionpesca.bd.BD;
import br.fusionpesca.comandos.Pesca;
import br.fusionpesca.encantamentos.Lucro;
import br.fusionpesca.encantamentos.Milagre;
import br.fusionpesca.encantamentos.eventos.LucroEvento;
import br.fusionpesca.encantamentos.eventos.MilagreEvento;
import br.fusionpesca.eventos.ClicarBotaoMenu;
import br.fusionpesca.eventos.JogadorPescar;
import br.fusionpesca.eventos.JogadorPescarSucesso;
import br.fusionpesca.eventos.JogadorSair;
import br.fusionpesca.eventos.cancelados.DroparItem;
import br.fusionpesca.eventos.cancelados.JogadorTeleportar;
import br.fusionpesca.eventos.cancelados.UsarComando;
import br.fusionpesca.gui.eventos.EventosMenuLojaVaras;
import br.fusionpesca.gui.eventos.EventosMenuMinhasVaras;
import br.fusionpesca.gui.eventos.EventosMenuPrincipal;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static Main plugin;

    public BD bd;

    public File configFileLoc;
    public YamlConfiguration configLoc;
    public File configFileVaras;
    public YamlConfiguration configVaras;

    public List<Enchantment> encantamentos;

    public Enchantment lucro;
    public Enchantment milagre;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        bd = new BD();
        salvarLocations();
        salvarVaras();
        registrarEncantamentos();
        getCommand("pesca").setExecutor(new Pesca());
        registrarEventos();
    }

    @Override
    public void onDisable() {

    }


    private void registrarEventos() {
        getServer().getPluginManager().registerEvents(new DroparItem(), this);
        getServer().getPluginManager().registerEvents(new JogadorTeleportar(), this);
        getServer().getPluginManager().registerEvents(new UsarComando(), this);
        getServer().getPluginManager().registerEvents(new JogadorSair(), this);
        getServer().getPluginManager().registerEvents(new ClicarBotaoMenu(), this);
        getServer().getPluginManager().registerEvents(new EventosMenuPrincipal(), this);
        getServer().getPluginManager().registerEvents(new EventosMenuMinhasVaras(), this);
        getServer().getPluginManager().registerEvents(new EventosMenuLojaVaras(), this);
        getServer().getPluginManager().registerEvents(new JogadorPescar(), this);
        getServer().getPluginManager().registerEvents(new JogadorPescarSucesso(), this);
        getServer().getPluginManager().registerEvents(new LucroEvento(), this);
        getServer().getPluginManager().registerEvents(new MilagreEvento(), this);
    }


    private void salvarLocations() {
        configFileLoc = new File(getDataFolder(), "Locations.yml");
        if (!configFileLoc.exists()) {
            saveResource("Locations.yml", false);
        }
        configLoc = YamlConfiguration.loadConfiguration(configFileLoc);
        try {
            configLoc.save(configFileLoc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarVaras() {
        configFileVaras = new File(getDataFolder(), "varas.yml");
        if (!configFileVaras.exists()) {
            saveResource("varas.yml", false);
        }
        configVaras = YamlConfiguration.loadConfiguration(configFileVaras);
        try {
            configVaras.save(configFileVaras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registrarEncantamentos() {
        encantamentos = new ArrayList<>();
        lucro = new Lucro(201);
        milagre = new Milagre(202);
        encantamentos.add(lucro);
        encantamentos.add(milagre);
        try {
            Field accept = Enchantment.class.getDeclaredField("acceptingNew");
            accept.setAccessible(true);
            accept.setBoolean(null, true);
            encantamentos.forEach(Enchantment::registerEnchantment);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


}
