package br.fusionpesca.pescador;

import br.fusionpesca.gui.MenuMinhasVaras;
import br.fusionpesca.recompensas.Peixe;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Pescador {

    private final UUID ID;
    private final String NOME;
    private List<String> varas;
    private HashMap<Peixe, Integer> peixes;
    private String varaAtual;
    private MenuMinhasVaras menuMinhasVaras;

    public Pescador(UUID id, String nome, List<String> varas, String varaAtual) {
        this.ID = id;
        this.NOME = nome;
        this.varas = varas;
        this.varaAtual = varaAtual;
        this.peixes = new HashMap<>();
    }

    public void addVara(String vara){
        varas.add(vara);
    }

    public List<String> getVaras() {
        return varas;
    }

    public String getVaraAtual() {
        return varaAtual;
    }

    public void setVaraAtual(String vara) {
        this.varaAtual = vara;
    }

    public UUID getId() {
        return ID;
    }

    public String getNome() {
        return NOME;
    }

    public MenuMinhasVaras getMenuMinhasVaras() {
        return menuMinhasVaras;
    }

    public void setMenuMinhasVaras(MenuMinhasVaras menuMinhasVaras) {
        this.menuMinhasVaras = menuMinhasVaras;
    }

    public void addPeixe(Peixe peixe){
        if(!peixes.containsKey(peixe)){
            peixes.put(peixe, 1);
        }else{

        }
        this.peixes.put(peixe, +1);
    }

    public HashMap<Peixe, Integer> getPeixes(){
        return this.peixes;
    }

    public Integer getPeixe(Peixe peixe){
        return peixes.get(peixe) == null ? 0 : peixes.get(peixe);
    }
}
