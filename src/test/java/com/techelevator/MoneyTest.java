package com.techelevator;

import com.techelevator.view.Money;
import org.junit.*;


public class MoneyTest {

    private Money testTarget;

    @Before
    public void set_up(){
        testTarget = new Money();
    }

    @Test
    public void make_change(){
        testTarget.makeChange(626.40);
        Assert.assertEquals(6,testTarget.getNumberOfHundredDollarBills());
        Assert.assertEquals(0, testTarget.getNumberOfFiftyDollarBills());
        Assert.assertEquals(1, testTarget.getNumberOfTwentyDollarBills());
        Assert.assertEquals(0, testTarget.getNumberOfTenDollarBills());
        Assert.assertEquals(1, testTarget.getNumberOfFiveDollarBills());
        Assert.assertEquals(1, testTarget.getNumberOfOneDollarBills());
        Assert.assertEquals(1, testTarget.getNumberOfQuarters());
        Assert.assertEquals(1, testTarget.getNumberOfDimes());
        Assert.assertEquals(1, testTarget.getNumberOfNickels());
    }
}
