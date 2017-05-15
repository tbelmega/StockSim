package de.belmega.stocksim.trade;

import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static de.belmega.stocksim.trade.TradeTestData.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PricingTest {

    @Test
    public void testThatLowestAvailableBidPriceIsPickedForAsk() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        placeBidAtMarket(fooCorpMarket, 8, EUR, 100);
        placeBidAtMarket(fooCorpMarket, 6, EUR, 100);
        placeBidAtMarket(fooCorpMarket, 7, EUR, 100);

        Ask ask = Ask.create(ALICE, Money.of(9, EUR));
        ask.setNumberOfShares(100);

        //act
        fooCorpMarket.place(ask);

        //assert
        MonetaryAmount amountPaid = ask.getTransaction().get().getMonetaryAmount();
        assertThat(amountPaid, is(equalTo(Money.of(600, EUR))));
    }

    private void placeBidAtMarket(StockMarket market, int number, CurrencyUnit eur, int numberOfShares) {
        Bid bid1 = Bid.create(BOB, Money.of(number, eur));
        bid1.setNumberOfShares(numberOfShares);
        market.place(bid1);
    }
}
