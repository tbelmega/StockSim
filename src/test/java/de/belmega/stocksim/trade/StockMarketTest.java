package de.belmega.stocksim.trade;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;
import org.javamoney.moneta.Money;
import org.junit.Test;

import java.time.LocalDateTime;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static de.belmega.stocksim.trade.TradeTestData.ALICE;
import static de.belmega.stocksim.trade.TradeTestData.BOB;
import static de.belmega.stocksim.trade.TradeTestData.FOOCORP_STOCK;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StockMarketTest {


    @Test
    public void testThatOrderIsPlaced() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid order = Bid.create(null, null);

        //act
        fooCorpMarket.place(order);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(1)));
    }

    @Test
    public void testThatOrderIsCanceled() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid order = Bid.create(null, null);
        fooCorpMarket.place(order);

        //act
        fooCorpMarket.cancel(order);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsCanceledWhenExpired() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid order = Bid.create(null, null);
        order.setExpiryDate(LocalDateTime.now().minusMinutes(1));

        //act
        fooCorpMarket.place(order);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsExecutedAtEqualPrices() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid bid = Bid.create(BOB, Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = Ask.create(ALICE, Money.of(10, EUR));
        ask.setNumberOfShares(100);

        //act
        fooCorpMarket.place(bid);
        fooCorpMarket.place(ask);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(0)));
        assertThat(fooCorpMarket.getAsks().size(), is(equalTo(0)));
    }

    @Test
    public void testThatOrderIsExecutedWhenAskPriceIsHigherThanBidPrice() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid bid = Bid.create(ALICE, Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = Ask.create(BOB, Money.of(11, EUR));
        ask.setNumberOfShares(100);

        //act
        fooCorpMarket.place(bid);
        fooCorpMarket.place(ask);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(0)));
        assertThat(fooCorpMarket.getAsks().size(), is(equalTo(0)));
    }


    @Test
    public void testThatOrderIsNotExecutedWhenAskPriceIsLowerThanBidPrice() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid bid = Bid.create(null, Money.of(10, EUR));
        bid.setNumberOfShares(100);

        Ask ask = Ask.create(null, Money.of(9, EUR));
        ask.setNumberOfShares(100);

        //act
        fooCorpMarket.place(bid);
        fooCorpMarket.place(ask);

        //assert
        assertThat(fooCorpMarket.getBids().size(), is(equalTo(1)));
        assertThat(fooCorpMarket.getAsks().size(), is(equalTo(1)));
    }
}
