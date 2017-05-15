package de.belmega.stocksim.trade.transaction;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;
import de.belmega.stocksim.trade.transaction.ShareTransaction;
import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.CurrencyUnit;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class ShareTransactionTest {

    @Test
    public void testThatTransactionIsExecuted() throws Exception {
        //arrange
        Stock stock = new Stock();

        BrokerAccount alice = createTestAccount(100, stock, 1000, EUR);
        BrokerAccount bob = createTestAccount(50, stock, 2000, EUR);

        ShareTransaction transaction = new ShareTransaction();
        transaction.transfer(100, stock, alice, bob);
        transaction.transfer(Money.of(2000, EUR),  bob, alice);

        //act
        transaction.commit();


        //assert
        assertThat(alice.getShares(stock), is(equalTo(0L)));
        assertThat(bob.getShares(stock), is(equalTo(150L)));
        assertThat(alice.getBalance(), is(equalTo(Money.of(3000, EUR))));
        assertThat(bob.getBalance(), is(equalTo(Money.of(0, EUR))));
    }

    private BrokerAccount createTestAccount(int amountOfStock, Stock stock, int amountOfMoney, CurrencyUnit currencyUnit) {
        BrokerAccount account = new BrokerAccount();
        account.deposit(amountOfStock, stock);
        account.deposit(Money.of(amountOfMoney, currencyUnit));
        return account;
    }
}
