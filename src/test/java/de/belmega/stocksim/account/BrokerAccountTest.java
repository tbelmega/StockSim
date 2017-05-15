package de.belmega.stocksim.account;

import de.belmega.stocksim.stock.Stock;
import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;

import static de.belmega.stocksim.account.BrokerAccount.EUR;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BrokerAccountTest {


    @Test
    public void testThatAccountHoldsBalance() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();

        //act
        MonetaryAmount amount = account.getBalance();

        //assert
        assertThat(amount, is(equalTo(Money.of(0, EUR))));
    }

    @Test
    public void testThatDepositIncreasesBalance() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();

        //act
        account.deposit(Money.of(10, EUR));
        account.deposit(Money.of(20, EUR));

        //assert
        MonetaryAmount amount = account.getBalance();
        assertThat(amount, is(equalTo(Money.of(30, EUR))));
    }

    @Test
    public void testThatWithdrawalDecreasesBalance() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        account.deposit(Money.of(20, EUR));

        //act
        account.withdraw(Money.of(10, EUR));

        //assert
        MonetaryAmount amount = account.getBalance();
        assertThat(amount, is(equalTo(Money.of(10, EUR))));
    }

    @Test
    public void testThatWithdrawalIsNotExecutedWhenBalanceTooLow() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        account.deposit(Money.of(20, EUR));

        //act
        account.withdraw(Money.of(30, EUR));

        //assert
        MonetaryAmount amount = account.getBalance();
        assertThat(amount, is(equalTo(Money.of(20, EUR))));
    }

    @Test
    public void testThatWithdrawalReturnsTrueWhenPossible() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        account.deposit(Money.of(20, EUR));

        //act
        boolean possible = account.withdraw(Money.of(10, EUR));

        //assert
        assertThat(possible, is(equalTo(true)));
    }

    @Test
    public void testThatWithdrawalReturnsFalseWhenNotPossible() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        account.deposit(Money.of(20, EUR));

        //act
        boolean possible = account.withdraw(Money.of(30, EUR));

        //assert
        assertThat(possible, is(equalTo(false)));
    }

    @Test
    public void testThatAccountHoldsShares() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        Stock stock = new Stock();

        //act
        account.deposit(100, stock);
        account.deposit(100, stock);

        //assert
        assertThat(account.getShares(stock), is(equalTo(200L)));
    }

    @Test
    public void testThatSharesAreWithdrawnFromAccount() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        Stock stock = new Stock();
        account.deposit(100, stock);

        //act
        account.withdraw(50, stock);

        //assert
        assertThat(account.getShares(stock), is(equalTo(50L)));
    }

    @Test
    public void testThatSharesAreNotWithdrawnFromAccountWhenNotPossible() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        Stock stock = new Stock();
        account.deposit(50, stock);

        //act
        account.withdraw(100, stock);

        //assert
        assertThat(account.getShares(stock), is(equalTo(50L)));
    }

    @Test
    public void testThatStockWithdrawalReturnsTrueWhenPossible() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        Stock stock = new Stock();
        account.deposit(100, stock);

        //act
        boolean possible = account.withdraw(50, stock);

        //assert
        assertThat(possible, is(equalTo(true)));
    }

    @Test
    public void testThatStockWithdrawalReturnsFalseWhenNotPossible() throws Exception {
        //arrange
        BrokerAccount account = new BrokerAccount();
        Stock stock = new Stock();
        account.deposit(50, stock);

        //act
        boolean possible = account.withdraw(100, stock);

        //assert
        assertThat(possible, is(equalTo(false)));
    }


    
}
