package com.ignaciosuay.service;

import com.ignaciosuay.domain.Coin;

import java.nio.file.Path;
import java.util.Map;

/**
 * Interface for managing the inventory
 */
public interface InventoryService {

    /**
     * Returns the number of coins available
     * @param inventoryPath path to the inventory file
     * @return a map with the number of coins available
     */
    Map<Coin,Integer> loadInventory(Path inventoryPath);

    /**
     * Updates the inventory
     * @param inventoryPath path to the inventory file
     * @param inventoryChange map which contains the number of coins in the inventory
     */
    void updateInventory(Path inventoryPath, Map<Coin,Integer> inventoryChange);
}
