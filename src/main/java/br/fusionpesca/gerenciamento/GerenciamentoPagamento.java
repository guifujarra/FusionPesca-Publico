package br.fusionpesca.gerenciamento;

import br.fusionpesca.Main;
import br.fusionpesca.pescador.Pescador;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;
import java.util.Map;

public class GerenciamentoPagamento {
    public static Economy econ;

    private static Map<Pescador, Double> pagamentosPescadores = new HashMap<>();
    static{
        setupEconomy();
    }


    public static void adicionarPagamento(Pescador pescador, Double valor){
        pagamentosPescadores.put(pescador, pagamentosPescadores.getOrDefault(pescador, valor) + valor);
    }

    public static double getPagamentoJogador(Pescador p){
        return pagamentosPescadores.get(p);
    }

    public static void removerJogadorPagamento(Pescador p){
        pagamentosPescadores.remove(p);
    }

    public static void definirNovoPagamento(Pescador pescador, double valor){
        pagamentosPescadores.computeIfPresent(pescador, (key, val) -> valor);
    }

    private static void setupEconomy() {
        if (Main.plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return;
        }
        RegisteredServiceProvider<Economy> rsp = Main.plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return;
        }
        econ = rsp.getProvider();
    }

}
