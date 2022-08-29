package com.techelevator.view;

import com.techelevator.items.CateringItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the catering system.
 */
public class Menu {

    private String mainMenuSelection;
    private String orderMenuSelection;
    private double depositAmount;

    private static final Scanner userInput = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("*************************");
        System.out.println("**     Weyland Corp.   **");
        System.out.println("**      Catering       **");
        System.out.println("*************************");
        System.out.println();
    }

    // Main menu

    public void showMainMenuOptions() {
        System.out.println("Main Menu: \n");
        System.out.println("1. Display catering inventory");
        System.out.println("2. Order");
        System.out.println("3. Quit program\n");
    }

    public String getMainMenuUserSelection() {
        System.out.println("Select one of the options above >>> ");
        this.mainMenuSelection = userInput.nextLine();
        return mainMenuSelection;
    }

    public void thankYouMessage() {
        System.out.println("Thank you for shopping with us!");
    }

    // Display items for sale

    public void showItemsForSale(Map<String, CateringItem> inventory) {

        System.out.println("Catering items for sale: \n");

        System.out.printf("%-22s%-22s%-22s%-22s\n", "Product Code", "Description", "Quantity", "Price");

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        for (Map.Entry<String, CateringItem> mapEntry : inventory.entrySet()) {

            String formattedPrice = formatter.format(mapEntry.getValue().getProductPrice());
            String formattedQuantity = String.valueOf(mapEntry.getValue().getProductQuantityAvailable());
            if (formattedQuantity.equals("0")) {
                formattedQuantity = "SOLD OUT";
            }

            System.out.printf("%-22s%-22s%-22s%-22s\n", mapEntry.getValue().getProductCode(),

                    mapEntry.getValue().getProductName(),
                    formattedQuantity,
                    (formattedPrice));
        }
        System.out.println();

    }

    public void displayInvalidOptionError() {
        System.out.println("Invalid menu selection. Please try again.\n");
    }

    // Order menu

    public void showOrderMenuOptions(double accountBalance) {
        System.out.println("Order Menu: \n");
        System.out.println("1. Add Money");
        System.out.println("2. Select Products");
        System.out.println("3. Complete Transaction");
        System.out.println("4. Return to Main Menu\n");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedBalance = formatter.format(accountBalance);
        System.out.println("Current Account Balance: " + formattedBalance);
    }

    public String getOrderMenuUserSelection() {
        System.out.println("Select one of the options above >>> ");
        this.orderMenuSelection = userInput.nextLine();
        return orderMenuSelection;
    }

    // Add money menu

    public void showAddMoneyMenu() {
        System.out.println("Please enter amount to deposit (maximum $500 per deposit).\n");
        System.out.println("Maximum account balance may not exceed $1,500.\n");
    }

    public double getDepositAmount() throws NumberFormatException {
        System.out.println("Enter a whole dollar amount or \"exit\" to return to the order menu >>> ");
        String input = userInput.nextLine();
        depositAmount = Double.parseDouble(input);
        return depositAmount;
    }

    public void showMaximumAccountBalanceErrorMessage() {
        System.out.println("Your deposit will exceed the maximum account balance of $1,500. Please re-enter a lower deposit amount.\n");
    }

    public void showMaximumDepositAmountErrorMessage() {
        System.out.println("We are unable to accept deposits greater than $500. Please re-enter a lower deposit amount.\n");
    }

    public void showInvalidDepositAmountErrorMessage() {
        System.out.println("We are only able to accept deposits $1 or greater. Please re-enter a deposit amount.\n");
    }

    public void successfulDepositMessage(double depositAmount, double accountBalance) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedBalance = formatter.format(accountBalance);
        String formattedDepositAmount = formatter.format(depositAmount);
        System.out.println("Your deposit in the amount of " + formattedDepositAmount + " was successful. Your current account balance is " + formattedBalance + ".");
    }

    // Select products menu

    public void showProductsMenu(Map<String, CateringItem> inventory) {
        System.out.println("Please see the inventory below and select a product code to purchase: \n");
        showItemsForSale(inventory);
    }

    public String getProductCodeForPurchase(Map<String, CateringItem> inventory) {
        System.out.println("Enter a product code from the list above (Example: A1) or type \"exit\" to return to the order menu >>> ");
        String input = userInput.nextLine().toUpperCase();
        String productCodeForPurchase = "";
        if (input.equals("EXIT")) {
            productCodeForPurchase = input;
        } else {
            try {
                productCodeForPurchase = inventory.get(input).getProductCode();
            } catch (NullPointerException e) {
                System.out.println("No such product exists. Please try again.\n");
            }
        }
        return productCodeForPurchase;
    }

    public void showSoldOutErrorMessage() {
        System.out.println("Sorry! This product is sold out. Please select another option from the menu below.\n");
    }

    public int getQuantityForPurchase() {
        System.out.println("Enter a quantity to purchase >>> ");
        String input = userInput.nextLine();
        int quantityForPurchase = 0;
        try {
            quantityForPurchase = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            System.out.println("Whoops! Please enter a whole number. \n");
        }
        return quantityForPurchase;
    }

    public void showQuantityNotAvailable() {
        System.out.println("Sorry we do not have this item available in the quantity you have requested. Please try again.\n");
    }

    public void showNegativeNumberEnteredErrorMessage() {
        System.out.println("Whoops!!!! Seems like you entered a negative number! Please try again.\n");
    }

    public void showInsufficientAmountOfMoneyError() {
        System.out.println("Not enough money. Please deposit additional funds and try again. \n");
    }

    public void showAddedToCartConformation(String productCode, Map<String, CateringItem> inventory, int productQuantity, double accountBalance) {
        String nameOfProduct = inventory.get(productCode).getProductName();

        System.out.println("We have successfully added " + productQuantity + " " + nameOfProduct + "(s) to your cart! \n");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formattedBalance = formatter.format(accountBalance);
        System.out.println("Your new account balance is: " + formattedBalance);
    }

    public String getCheckOutConfirmation() {
        System.out.println(" Are you sure you want to complete your order? (Y/N) >>> ");
        String input = userInput.nextLine().toUpperCase();
        return input;
    }

    public void showCustomerReceipt(Map<String, Integer> itemsWithinCartUponCompletingPurchase, Map<String, CateringItem> inventory) {

        System.out.println("Your Receipt: \n");

        System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", "Quantity", "Product Type", "Description", "Price", "Total Cost", "Additional Recommendations");

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        for (Map.Entry<String, Integer> mapEntry : itemsWithinCartUponCompletingPurchase.entrySet()) {

            String formattedPrice = formatter.format(inventory.get(mapEntry.getKey()).getProductPrice());

            String productType = inventory.get(mapEntry.getKey()).getProductType();
            double totalCostOfItem = (mapEntry.getValue()) * (inventory.get(mapEntry.getKey()).getProductPrice());
            String formattedTotalCostOfItem = formatter.format(totalCostOfItem);

            if (productType.equals("Beverage")) {

                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", mapEntry.getValue(),

                        productType,
                        inventory.get(mapEntry.getKey()).getProductName(),
                        formattedPrice,
                        formattedTotalCostOfItem,
                        "Don't Forget Ice.");

            } else if (productType.equals("Appetizer")) {

                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", mapEntry.getValue(),

                        productType,
                        inventory.get(mapEntry.getKey()).getProductName(),
                        formattedPrice,
                        formattedTotalCostOfItem,
                        "You Might Need Extra Plates.");

            } else if (productType.equals("Entree")) {

                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", mapEntry.getValue(),

                        productType,
                        inventory.get(mapEntry.getKey()).getProductName(),
                        formattedPrice,
                        formattedTotalCostOfItem,
                        "Did you remember Dessert?");

            } else if (productType.equals("Dessert")) {

                System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n", mapEntry.getValue(),

                        productType,
                        inventory.get(mapEntry.getKey()).getProductName(),
                        formattedPrice,
                        formattedTotalCostOfItem,
                        "Coffee goes with Dessert.");

            }

        }
        System.out.println();

    }

	public void showChangeGiven(Money money){
		System.out.print("You received ");
		if (money.getNumberOfHundredDollarBills() > 0){
			System.out.print("(" + money.getNumberOfHundredDollarBills() + ") Hundreds ");
		}
		if (money.getNumberOfFiftyDollarBills() > 0){
			System.out.print("(" + money.getNumberOfFiftyDollarBills() + ") Fifties ");
		}
		if (money.getNumberOfTwentyDollarBills() > 0){
			System.out.print("(" + money.getNumberOfTwentyDollarBills() + ") Twenties ");
		}
		if (money.getNumberOfTenDollarBills() > 0){
			System.out.print("(" + money.getNumberOfTenDollarBills() + ") Tens ");
		}
		if (money.getNumberOfFiveDollarBills() > 0){
			System.out.print("(" + money.getNumberOfFiveDollarBills() + ") Fives ");
		}
		if (money.getNumberOfOneDollarBills() > 0){
			System.out.print("(" + money.getNumberOfOneDollarBills() + ") Ones ");
		}
		if (money.getNumberOfQuarters() > 0){
			System.out.print("(" + money.getNumberOfQuarters() + ") Quarters ");
		}
		if (money.getNumberOfDimes() > 0){
			System.out.print("(" + money.getNumberOfDimes() + ") Dimes ");
		}
		if (money.getNumberOfNickels() > 0){
			System.out.print("(" + money.getNumberOfNickels() + ") Nickels ");
		}
		System.out.print("in change\n\n");
	}

}
