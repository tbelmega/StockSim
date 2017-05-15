package de.belmega.stocksim.trade.transaction;

import de.belmega.stocksim.account.BrokerAccount;

public abstract class Transfer {
    protected final BrokerAccount account;
    protected final Type operation;

    public abstract void execute();

    public enum Type { DEPOSIT, WITHDRAW }


    public Transfer(BrokerAccount account, Type operation) {
        this.account = account;
        this.operation = operation;
    }

}
