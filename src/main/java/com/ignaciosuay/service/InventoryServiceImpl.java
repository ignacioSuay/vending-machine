package com.ignaciosuay.service;

import com.ignaciosuay.domain.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.nio.file.AccessMode.WRITE;

/**
 * Created by natxo on 12/11/16.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Override
    public Map<Coin,Integer> loadInventory(Path path){
        Map<Coin,Integer> inventoryCoins = new EnumMap<>(Coin.class);

        try(BufferedReader br = Files.newBufferedReader(path)) {
            br.lines()
                    .map(l -> l.split("="))
                    .forEach(prop ->inventoryCoins.put(Coin.getCoin(Integer.parseInt(prop[0])), Integer.parseInt(prop[1])));
        } catch (IOException ioException) {
            log.error("Error while reading the inventory properties file", ioException);
        } catch (Exception e){
            log.error("Error loading the inventory properties", e);
        }
        return inventoryCoins;
    }


    @Override
    public void updateInventory(Path path, Map<Coin, Integer> inventory) {
        try(BufferedWriter bw = Files.newBufferedWriter(path)) {
            String properties = inventory.entrySet().stream()
                    .map(e -> e.getKey().getDenomination() + "=" + e.getValue())
                    .collect(Collectors.joining("\n"));
            bw.write(properties);
        }catch (IOException ioException){
            log.error("Error while writing properties to file", ioException);
        }catch (Exception e){
            log.error("Cannot store inventory properties", e);
        }
    }

}
