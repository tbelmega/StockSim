package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static junit.framework.TestCase.fail;

public class PricingTest {

    @Test
    public void testThatLowestAvailableBidPriceIsPickedForAsk() throws Exception {
        //arrange
        StockMarket market = new StockMarket();

        placeBidAtMarket(market, 8, EUR, 100);
        placeBidAtMarket(market, 6, EUR, 100);
        placeBidAtMarket(market, 7, EUR, 100);

        Ask ask = new Ask(Money.of(9, EUR));
        ask.setNumberOfShares(100);

        //act
        market.place(ask);

        //assert
        fail();
    }

    private void placeBidAtMarket(StockMarket market, int number, CurrencyUnit eur, int numberOfShares) {
        Bid bid1 = new Bid(Money.of(number, eur));
        bid1.setNumberOfShares(numberOfShares);
        market.place(bid1);
    }
}
