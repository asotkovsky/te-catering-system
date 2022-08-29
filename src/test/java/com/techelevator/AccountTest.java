package com.techelevator;

import com.techelevator.view.Account;
import org.junit.*;

public class AccountTest {
    private Account testTarget;

    @Before
    public void setup(){
        testTarget = new Account();
    }

    @Test

    public void negative_number_deposit(){
        double actualBalance = testTarget.deposit(-1);
        Assert.assertEquals(0d, actualBalance,.009);
    }

    @Test

    public void deposit_above_500(){
        double actualBalance = testTarget.deposit(501);
        Assert.assertEquals(0d,actualBalance,.009);
    }

    @Test

    public void balance_above_1500(){
        testTarget.setAccountBalance(1500);
        double actualBalance = testTarget.deposit(1);
        Assert.assertEquals(1500d,actualBalance,.009);
    }

    @Test

    public void deposit_of_100_dollars(){
        double actualBalance = testTarget.deposit(100);
        Assert.assertEquals(100d,actualBalance,.009);
    }

    @Test public void twenty_five_dollar_purchase_100_dollar_balance_returns_75 (){
        testTarget.setAccountBalance(100d);
        double actualNewAccountBalance = testTarget.updateAccountBalanceAfterPurchase(testTarget, 25d);
        Assert.assertEquals(75, actualNewAccountBalance, 0.009);
    }

}
