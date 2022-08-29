package com.techelevator.view;

public class Money {

    private int numberOfHundredDollarBills = 0;
    private int numberOfFiftyDollarBills = 0;
    private int numberOfTwentyDollarBills = 0;
    private int numberOfTenDollarBills = 0;
    private int numberOfFiveDollarBills = 0;
    private int numberOfOneDollarBills = 0;
    private int numberOfQuarters = 0;
    private int numberOfDimes = 0;
    private int numberOfNickels = 0;


    public void makeChange(double accountBalance){

        int remainder;
        double accountBalanceInCents  = accountBalance * 100d;

        this.numberOfHundredDollarBills = (int) Math.floor(accountBalanceInCents / 10000);
        remainder = (int) accountBalanceInCents % 10000;

        this.numberOfFiftyDollarBills = (int) Math.floor(remainder / 5000);
        remainder = (int) remainder % 5000;

        this.numberOfTwentyDollarBills = (int) Math.floor(remainder / 2000);
        remainder = (int) remainder % 2000;

        this.numberOfTenDollarBills = (int) Math.floor(remainder / 1000);
        remainder = (int) remainder % 1000;

        this.numberOfFiveDollarBills = (int) Math.floor(remainder / 500);
        remainder = (int) remainder % 500;

        this.numberOfOneDollarBills = (int) Math.floor(remainder / 100);
        remainder = (int) remainder % 100;

        this.numberOfQuarters = (int) Math.floor(remainder / 25);
        remainder = (int) remainder % 25;

        this.numberOfDimes = (int) Math.floor(remainder / 10);
        remainder = (int) remainder % 10;

        this.numberOfNickels = (int) Math.floor(remainder / 5);
        remainder = (int) remainder % 5;

    }

    public int getNumberOfHundredDollarBills() {
        return numberOfHundredDollarBills;
    }

    public int getNumberOfFiftyDollarBills() {
        return numberOfFiftyDollarBills;
    }

    public int getNumberOfTwentyDollarBills() {
        return numberOfTwentyDollarBills;
    }

    public int getNumberOfTenDollarBills() {
        return numberOfTenDollarBills;
    }

    public int getNumberOfFiveDollarBills() {
        return numberOfFiveDollarBills;
    }

    public int getNumberOfOneDollarBills() {
        return numberOfOneDollarBills;
    }

    public int getNumberOfQuarters() {
        return numberOfQuarters;
    }

    public int getNumberOfDimes() {
        return numberOfDimes;
    }

    public int getNumberOfNickels() {
        return numberOfNickels;
    }

}
