package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryCurrencies;

import static org.wildfly.common.Assert.assertTrue;

public class AskTest {


    @Test
    public void testThatAskMayHaveALowerLimit() throws Exception {
        //arrange
        Ask ask = new Ask();

        //act
        ask.setLowerLimit(Money.of(10, MonetaryCurrencies.getCurrency("EUR")));

        //assert
        assertTrue(ask.getUpperLimit().isPresent());
    }
}
