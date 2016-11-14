package com.ignaciosuay.service;

import com.ignaciosuay.domain.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Implementation for Validating machine service
 */
@Service
public class VendingMachineServiceImpl implements VendingMachineService {

    private static final Logger log = LoggerFactory.getLogger(VendingMachineServiceImpl.class);

    public static final Path INVENTORY_FILE_PATH = Paths.get("src/main/resources/coin-inventory.properties");

    @Inject
    InventoryService inventoryService;

    @Override
    public Collection<Coin> getOptimalChangeFor(int pence) {
        if(pence < 0) {
            log.error("Cannot get optimal change for negative pence value");
            throw new IllegalArgumentException("Pence value needs to be greater than 0");
        }
        Collection<Coin> optimalCoins = new ArrayList<>();
        Iterator<Coin> coinIt = Coin.iterator();
        while(pence > 0 && coinIt.hasNext()){
            Coin coin = coinIt.next();
            int numberCoins = numberCoinsNeeded(pence,coin);
            if(numberCoins > 0){
                optimalCoins.addAll(Collections.nCopies(numberCoins, coin));
                pence = decreaseValue(pence, coin, numberCoins);
            }
        }
        return optimalCoins;
    }

    @Override
    public Collection<Coin> getChangeFor(int pence) {
        Map<Coin, Integer> availableChange = inventoryService.loadInventory(INVENTORY_FILE_PATH);
        Collection<Coin> change = calculateChange(pence, availableChange);
        inventoryService.updateInventory(INVENTORY_FILE_PATH, availableChange);
        return change;
    }

    private int numberCoinsNeeded(int pence, Coin coin){
        return pence / coin.getDenomination();
    }

    private int decreaseValue(int pence, Coin coin, int numberCoins){
        return pence - (coin.getDenomination() * numberCoins);
    }

    private Collection<Coin> calculateChange(int pence, Map<Coin, Integer> availableChange){
        Collection<Coin> change = new ArrayList<>();
        Iterator<Coin> coinIt = Coin.iterator();
        while(pence > 0 && coinIt.hasNext()){
            Coin coin = coinIt.next();
            int numCoinsNeeded = numberCoinsNeeded(pence,coin);
            if (numCoinsNeeded > 0)  {
                int retrieveNumCoins = retrieveNumberCoinsAvailable(numCoinsNeeded, coin, availableChange);
                change.addAll(Collections.nCopies(retrieveNumCoins, coin));
                pence = decreaseValue(pence, coin, retrieveNumCoins);
            }
        }
        if(pence > 0) {
            log.error("There is not enough change");
            throw new InsufficientCoinageException("There is not enough change");
        }
        return change;
    }

    private int retrieveNumberCoinsAvailable(int numCoinsNeeded, Coin coin, Map<Coin, Integer> availableChange){
        Integer numberCoinsAvailable = availableChange.get(coin);
        int retrieveNumCoins = numCoinsNeeded < numberCoinsAvailable ? numCoinsNeeded : numberCoinsAvailable;
        availableChange.put(coin, numberCoinsAvailable - retrieveNumCoins);
        return retrieveNumCoins;
    }

}
