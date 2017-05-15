package de.belmega.stocksim.trade;

import de.belmega.stocksim.account.BrokerAccount;
import de.belmega.stocksim.stock.Stock;
import org.javamoney.moneta.Money;

import static de.belmega.stocksim.account.BrokerAccount.EUR;

public class TradeTestData {

    public static final Stock FOOCORP_STOCK = new Stock();
    public static final BrokerAccount ALICE = createTestBrokerAccount(FOOCORP_STOCK);
    public static final BrokerAccount BOB = createTestBrokerAccount(FOOCORP_STOCK);


    private static BrokerAccount createTestBrokerAccount(Stock stock) {
        BrokerAccount account = new BrokerAccount();
        account.deposit(1000, stock);
        account.deposit(Money.of(10000, EUR));
        return account;
    }
}
