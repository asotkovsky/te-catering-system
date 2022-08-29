package com.techelevator.view;

import com.techelevator.filereader.LogFileWriter;

public class Account {

    private double accountBalance = 0;
    private double depositAmount;
    private boolean addingMoney = true;
    Menu menu = new Menu();
    LogFileWriter logFileWriter = new LogFileWriter();

    // Constructor
    public Account() {

    }

    public double deposit(double depositAmount) {

        if (depositAmount <= 0) {
            menu.showInvalidDepositAmountErrorMessage();
        } else if ((accountBalance + depositAmount <= 1500d) && (depositAmount <= 500d)) {
            this.accountBalance += depositAmount;
            menu.successfulDepositMessage(depositAmount, accountBalance);
            logFileWriter.logAddMoney(depositAmount, accountBalance);
        } else if (depositAmount > 500d) {
            menu.showMaximumDepositAmountErrorMessage();
        } else if (accountBalance + depositAmount > 1500d) {
            menu.showMaximumAccountBalanceErrorMessage();
        }

        return accountBalance;
    }

    public double updateAccountBalanceAfterPurchase(Account account, double totalCostOfItemPurchase) {
        double accountBalance = getAccountBalance();
        double newAccountBalanceAfterPurchase = this.accountBalance - totalCostOfItemPurchase;
        account.setAccountBalance(newAccountBalanceAfterPurchase);
        return newAccountBalanceAfterPurchase;
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
