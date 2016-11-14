package com.ignaciosuay.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


/**
 * Coin enumeration tests
 */
public class CoinTest {

    @Test
    public void getCoin() throws Exception {
        Coin onePound = Coin.getCoin(100);
        assertThat(onePound).isNotNull();
        assertThat(onePound).isEqualTo(Coin.ONE_POUND);
        assertThat(Coin.getCoin(50)).isEqualTo(Coin.FIFTY_PENCE);
        assertThat(Coin.getCoin(20)).isEqualTo(Coin.TWENTY_PENCE);
        assertThat(Coin.getCoin(10)).isEqualTo(Coin.TEN_PENCE);
        assertThat(Coin.getCoin(5)).isEqualTo(Coin.FIVE_PENCE);
        assertThat(Coin.getCoin(2)).isEqualTo(Coin.TWO_PENCE);
        assertThat(Coin.getCoin(1)).isEqualTo(Coin.ONE_PENNY);
    }

    @Test
    public void getCoinInvalidValue() throws Exception {
        Throwable thrown = catchThrowable(() -> Coin.getCoin(200));
        assertThat(thrown.getClass()).isEqualTo(IllegalArgumentException.class);
    }

}