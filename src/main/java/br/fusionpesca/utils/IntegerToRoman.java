package br.fusionpesca.utils;

import java.util.TreeMap;

public class IntegerToRoman {
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

}
