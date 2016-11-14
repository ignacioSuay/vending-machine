package com.ignaciosuay.domain;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Enumeration of all the possible coins
 */
public enum Coin {
    ONE_POUND(100),
    FIFTY_PENCE(50),
    TWENTY_PENCE(20),
    TEN_PENCE(10),
    FIVE_PENCE(5),
    TWO_PENCE(2),
    ONE_PENNY(1);

    private final int denomination;

    Coin(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    public static Coin getCoin(int value) {
        for(Coin coin : values()) {
            if (coin.getDenomination() == value)
                return coin;
        }
        throw new IllegalArgumentException();
    }

    public static Iterator<Coin> iterator(){
        return Arrays.asList(Coin.values()).iterator();
    }

    @Override
    public String toString() {return Integer.toString(denomination);}
}
