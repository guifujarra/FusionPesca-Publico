package br.fusionpesca.vara;


import java.util.Comparator;

public class VaraComparator implements Comparator<Vara>{
    @Override
    public int compare(Vara v1, Vara v2) {
        return Integer.compare(v1.getId(), v2.getId());
    }

}
