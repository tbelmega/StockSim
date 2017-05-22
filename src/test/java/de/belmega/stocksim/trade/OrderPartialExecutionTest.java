package de.belmega.stocksim.trade;

import de.belmega.stocksim.trade.transaction.ShareTransaction;
import org.javamoney.moneta.Money;
import org.junit.Test;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static de.belmega.stocksim.trade.TradeTestData.ALICE;
import static de.belmega.stocksim.trade.TradeTestData.BOB;
import static de.belmega.stocksim.trade.TradeTestData.FOOCORP_STOCK;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OrderPartialExecutionTest {


    @Test
    public void testThatOrderIsExecutedPartially() throws Exception {
        //arrange
        StockMarket fooCorpMarket = new StockMarket(FOOCORP_STOCK);
        Bid bid = Bid.create(ALICE, Money.of(10, EUR));
        bid.setNumberOfShares(60);
        fooCorpMarket.place(bid);

        Ask ask = Ask.create(BOB, Money.of(11, EUR));
        ask.setNumberOfShares(100);

        //act
        fooCorpMarket.place(ask);

        //assert
        ShareTransaction transaction = ask.getTransaction().get();
        long stockAmount = transaction.getShareAmount();
        assertThat(stockAmount, is(equalTo(60L)));
    }
}
