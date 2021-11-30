package br.fusionpesca.recompensas;

public enum Peixe {
    TUBARAO(6000, 1),
    PEIXE_ESPADA(2600, 9),
    SALMAO(1500, 30),
    SARDINHA(550, 60);



    private final int valor;
    private final int chance;

    Peixe(final int novoValor, final int novaChance) {
        valor = novoValor;
        chance = novaChance;
    }

    public double getValor(){
        return valor;
    }

    public int getChance(){
        return chance;
    }
}
