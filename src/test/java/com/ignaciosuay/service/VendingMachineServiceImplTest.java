package com.ignaciosuay.service;

import com.ignaciosuay.TestData;
import com.ignaciosuay.VendingMachineApp;
import com.ignaciosuay.domain.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


/**
 * Vending machine service tests
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = VendingMachineApp.class)
public class VendingMachineServiceImplTest {

    @Inject
    VendingMachineServiceImpl vendingMachineService;

    @Inject
    InventoryService inventoryService;

    @Test
    public void getOptimalChangeFor75Pence() throws Exception {
        Collection<Coin> seventyFivePence = vendingMachineService.getOptimalChangeFor(75);

        Iterator<Coin> coinIt = seventyFivePence.iterator();
        assertThat(3).isEqualTo(seventyFivePence.size());
        assertThat(coinIt.next()).isEqualTo(Coin.FIFTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.TWENTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.FIVE_PENCE);
    }

    @Test
    public void getOptimalChangeFor2PoundsFifty() throws Exception {
        Collection<Coin> twoPoundsFifty = vendingMachineService.getOptimalChangeFor(250);

        Iterator<Coin> coinIt = twoPoundsFifty.iterator();
        assertThat(3).isEqualTo(twoPoundsFifty.size());
        assertThat(coinIt.next()).isEqualTo(Coin.ONE_POUND);
        assertThat(coinIt.next()).isEqualTo(Coin.ONE_POUND);
        assertThat(coinIt.next()).isEqualTo(Coin.FIFTY_PENCE);
    }

    @Test
    public void getOptionalChangeInvalidValue() throws Exception {
        Throwable thrown = catchThrowable(() -> vendingMachineService.getOptimalChangeFor(-10));
        assertThat(thrown.getClass()).isEqualTo(IllegalArgumentException.class);
    }


    @Test
    public void getChangeForOptimalAvailableCoins() throws Exception {
        Map<Coin, Integer> inventoryInitial = TestData.dumpInventoryData();
        inventoryService.updateInventory(VendingMachineServiceImpl.INVENTORY_FILE_PATH, inventoryInitial);
        Collection<Coin> fiftyFivePence = vendingMachineService.getChangeFor(55);

        Iterator<Coin> coinIt = fiftyFivePence.iterator();
        assertThat(2).isEqualTo(fiftyFivePence.size());
        assertThat(coinIt.next()).isEqualTo(Coin.FIFTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.FIVE_PENCE);

        Map<Coin, Integer> inventoryAfterChange = inventoryService.loadInventory(VendingMachineServiceImpl.INVENTORY_FILE_PATH);
        assertThat(inventoryAfterChange.get(Coin.FIFTY_PENCE)).isEqualTo(inventoryInitial.get(Coin.FIFTY_PENCE) - 1);
        assertThat(inventoryAfterChange.get(Coin.FIVE_PENCE)).isEqualTo(inventoryInitial.get(Coin.FIVE_PENCE) - 1);
    }

    @Test
    public void getChangeForNotOptimalAvailableCoins() throws Exception {
        Map<Coin, Integer> inventoryInitial = TestData.dumpInventoryData();
        inventoryService.updateInventory(VendingMachineServiceImpl.INVENTORY_FILE_PATH, inventoryInitial);
        Collection<Coin> twoPoundsFifty = vendingMachineService.getChangeFor(250);

        Iterator<Coin> coinIt = twoPoundsFifty.iterator();
        assertThat(6).isEqualTo(twoPoundsFifty.size());
        assertThat(coinIt.next()).isEqualTo(Coin.ONE_POUND);
        assertThat(coinIt.next()).isEqualTo(Coin.FIFTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.FIFTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.TWENTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.TWENTY_PENCE);
        assertThat(coinIt.next()).isEqualTo(Coin.TEN_PENCE);

        Map<Coin, Integer> inventoryAfterChange = inventoryService.loadInventory(VendingMachineServiceImpl.INVENTORY_FILE_PATH);
        assertThat(inventoryAfterChange.get(Coin.ONE_POUND)).isEqualTo(inventoryInitial.get(Coin.ONE_POUND) - 1);
        assertThat(inventoryAfterChange.get(Coin.FIFTY_PENCE)).isEqualTo(inventoryInitial.get(Coin.FIFTY_PENCE) - 2);
        assertThat(inventoryAfterChange.get(Coin.TWENTY_PENCE)).isEqualTo(inventoryInitial.get(Coin.TWENTY_PENCE) - 2);
        assertThat(inventoryAfterChange.get(Coin.TEN_PENCE)).isEqualTo(inventoryInitial.get(Coin.TEN_PENCE) - 1);
    }

    @Test
    public void getChangeForInsufficientCoinageException(){
        Map<Coin, Integer> inventoryInitial = TestData.dumpInventoryData();
        inventoryService.updateInventory(VendingMachineServiceImpl.INVENTORY_FILE_PATH, inventoryInitial);

        Throwable thrown = catchThrowable(() -> vendingMachineService.getChangeFor(400));
        assertThat(thrown.getClass()).isEqualTo(InsufficientCoinageException.class);
    }


}