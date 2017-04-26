package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryCurrencies;

import static org.wildfly.common.Assert.assertTrue;

public class BidTest {

    @Test
    public void testThatBidMayHaveAnUpperLimit() throws Exception {
        //arrange
        Bid bid = new Bid();

        //act
        bid.setUpperLimit(Money.of(10, MonetaryCurrencies.getCurrency("EUR")));

        //assert
        assertTrue(bid.getLowerLimit().isPresent());
    }
}
