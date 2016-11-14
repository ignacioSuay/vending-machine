package com.ignaciosuay;

import com.ignaciosuay.domain.Coin;

import java.util.EnumMap;
import java.util.Map;

/**
 * Test util for generating test data
 */
public class TestData {

    public static Map<Coin, Integer> dumpInventoryData(){
        Map<Coin, Integer> inventoryMap = new EnumMap<>(Coin.class);
        inventoryMap.put(Coin.ONE_POUND, 1);
        inventoryMap.put(Coin.FIFTY_PENCE, 2);
        inventoryMap.put(Coin.TWENTY_PENCE, 3);
        inventoryMap.put(Coin.TEN_PENCE, 4);
        inventoryMap.put(Coin.FIVE_PENCE, 5);
        inventoryMap.put(Coin.TWO_PENCE, 6);
        inventoryMap.put(Coin.ONE_PENNY, 7);
        return inventoryMap;
    }

}
