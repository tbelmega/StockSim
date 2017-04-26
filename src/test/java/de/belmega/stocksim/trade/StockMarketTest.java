package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StockMarketTest {

    public static final CurrencyUnit EUR = MonetaryCurrencies.getCurrency("EUR");

    @Test
    public void testThatOrderIsPlaced() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        //act
        Order order = new Bid();
        market.place(order);

        //assert
        assertThat(market.getBids().size(), is(equalTo(1)));
    }

    @Test
    public void testThatOrderIsCanceled() throws Exception {
        //arrange
        StockMarket market = new StockMarket();
        Order order = new Bid();
        market.place(order);

        //act
        market.cancel(order);

        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsCanceledWhenExpired() throws Exception {
        //arrange
        StockMarket market = new StockMarket();
        Order order = new Bid();
        order.setExpiryDate(LocalDateTime.now().minusMinutes(1));
        market.place(order);

        //act

        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsExecuted() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        Bid bid = new Bid();
        bid.setNumberOfShares(100);
        bid.setUpperLimit(Money.of(10, EUR));
        market.place(bid);

        Ask ask = new Ask();
        ask.setNumberOfShares(100);
        ask.setLowerLimit(Money.of(10, EUR));
        market.place(ask);

        //act


        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
        assertThat(market.getAsks().size(), is(equalTo(0)));
    }



}
