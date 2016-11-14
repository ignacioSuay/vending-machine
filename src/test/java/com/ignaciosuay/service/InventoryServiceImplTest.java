package com.ignaciosuay.service;

import com.ignaciosuay.TestData;
import com.ignaciosuay.VendingMachineApp;
import com.ignaciosuay.domain.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Inventory Services tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VendingMachineApp.class)
public class InventoryServiceImplTest {

    public static final Path TEST_INVENTORY_PATH_READ = Paths.get("src/test/resources/coin-inventory.read.properties");
    public static final Path TEST_INVENTORY_PATH_UPDATE = Paths.get("src/test/resources/coin-inventory.update.properties");

    @Inject
    InventoryService inventoryService;

    @Test
    public void loadInventory() throws Exception {
        Map<Coin, Integer> inventoryMap = inventoryService.loadInventory(TEST_INVENTORY_PATH_READ);

        assertThat(inventoryMap).isNotNull();
        assertThat(inventoryMap.get(Coin.ONE_POUND)).isEqualTo(11);
        assertThat(inventoryMap.get(Coin.FIFTY_PENCE)).isEqualTo(24);
        assertThat(inventoryMap.get(Coin.TWENTY_PENCE)).isEqualTo(0);
        assertThat(inventoryMap.get(Coin.TEN_PENCE)).isEqualTo(99);
        assertThat(inventoryMap.get(Coin.FIVE_PENCE)).isEqualTo(200);
        assertThat(inventoryMap.get(Coin.TWO_PENCE)).isEqualTo(11);
        assertThat(inventoryMap.get(Coin.ONE_PENNY)).isEqualTo(23);
    }

    @Test
    public void updateInventory() throws Exception {
        Map<Coin, Integer> inventoryMap = TestData.dumpInventoryData();
        inventoryService.updateInventory(TEST_INVENTORY_PATH_UPDATE, inventoryMap);

        Map<Coin, Integer> updatedInventory = inventoryService.loadInventory(TEST_INVENTORY_PATH_UPDATE);
        assertThat(1).isEqualTo(updatedInventory.get(Coin.ONE_POUND));
        assertThat(2).isEqualTo(updatedInventory.get(Coin.FIFTY_PENCE));
        assertThat(3).isEqualTo(updatedInventory.get(Coin.TWENTY_PENCE));
        assertThat(4).isEqualTo(updatedInventory.get(Coin.TEN_PENCE));
        assertThat(5).isEqualTo(updatedInventory.get(Coin.FIVE_PENCE));
        assertThat(6).isEqualTo(updatedInventory.get(Coin.TWO_PENCE));
        assertThat(7).isEqualTo(updatedInventory.get(Coin.ONE_PENNY));
    }

}