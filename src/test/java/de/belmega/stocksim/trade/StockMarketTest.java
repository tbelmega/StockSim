package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;
import java.time.LocalDateTime;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StockMarketTest {

    @Test
    public void testThatOrderIsPlaced() throws Exception {
        //arrange
        StockMarket market = new StockMarket();
        Order order = new Bid(null);

        //act
        market.place(order);

        //assert
        assertThat(market.getBids().size(), is(equalTo(1)));
    }

    @Test
    public void testThatOrderIsCanceled() throws Exception {
        //arrange
        StockMarket market = new StockMarket();
        Order order = new Bid(null);
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
        Order order = new Bid(null);
        order.setExpiryDate(LocalDateTime.now().minusMinutes(1));

        //act
        market.place(order);

        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsExecutedAtEqualPrices() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        Bid bid = new Bid(Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = new Ask(Money.of(10, EUR));
        ask.setNumberOfShares(100);

        //act
        market.place(bid);
        market.place(ask);


        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
        assertThat(market.getAsks().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsExecutedWhenAskPriceIsHigherThanBidPrice() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        Bid bid = new Bid(Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = new Ask(Money.of(11, EUR));
        ask.setNumberOfShares(100);

        //act
        market.place(bid);
        market.place(ask);


        //assert
        assertThat(market.getBids().size(), is(equalTo(0)));
        assertThat(market.getAsks().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsNotExecutedWhenAskPriceIsLowerThanBidPrice() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        Bid bid = new Bid(Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = new Ask(Money.of(9, EUR));
        ask.setNumberOfShares(100);

        //act
        market.place(bid);
        market.place(ask);


        //assert
        assertThat(market.getBids().size(), is(equalTo(1)));
        assertThat(market.getAsks().size(), is(equalTo(1)));
    }





}
