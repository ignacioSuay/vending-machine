package com.ignaciosuay.service;

import com.ignaciosuay.domain.Coin;

import java.util.Collection;

/**
 * Interface which represents a Vending Machine
 */
public interface VendingMachineService {

    /**
     * Returns the optimal change for a given number of pence
     * @param pence the number of given pence
     * @return a collection of coins which represents the change
     * @throws IllegalArgumentException if the number of pence is negative
     */
    Collection<Coin> getOptimalChangeFor(int pence);


    /**
     * Returns the change for a given number of pence and the coins avalable in the inventory
     * @param pence the number of given pence
     * @return a collection of coins which represents the change
     * @throws InsufficientCoinageException if there are not enough coins
     */
    Collection<Coin> getChangeFor(int pence);


}
