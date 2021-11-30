package br.fusionpesca.eventos.customizados;

import br.fusionpesca.recompensas.Peixe;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class JogadorPescarEvent extends Event {

    private final Player player;
    private final Peixe peixe;
    private double valorPesca;
    private final ItemStack vara;
    private static final HandlerList HANDLERS = new HandlerList();

    public JogadorPescarEvent(Player player, Peixe peixe, ItemStack vara){
        this.player = player;
        this.peixe = peixe;
        this.valorPesca = peixe.getValor();
        this.vara = vara;
    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public Player getPlayer() {
        return player;
    }

    public Peixe getPeixe() {
        return peixe;
    }

    public double getValorPesca() {
        return valorPesca;
    }

    public void setValorPesca(double valorPesca) {
        this.valorPesca = valorPesca;
    }

    public ItemStack getVara() {
        return vara;
    }
}
