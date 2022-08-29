package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.items.CateringItem;
import com.techelevator.view.Account;
import com.techelevator.view.Cart;
import com.techelevator.view.Menu;
import com.techelevator.view.Money;

import java.io.FileNotFoundException;
import java.util.Map;

/*
 * This class should control the workflow of the application, but not do any other work
 *
 * The menu class should communicate with the user, but do no other work
 *
 * This class should control the logical workflow of the application, but it should do no other
 * work.  It should communicate with the user (System.in and System.out) using the Menu class and ask
 * the CateringSystem class to do any work and pass the results between those 2 classes.
 */
public class CateringSystemCLI {

    /*
     * The menu class is instantiated in the main() method at the bottom of this file.
     * It is the only class instantiated in the starter code.
     * You will need to instantiate all other classes using the new keyword before you can use them.
     *
     * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
     */
    private Menu menu;
    private Account account = new Account();
    private InventoryFileReader inventoryReader = new InventoryFileReader();
    private Map<String, CateringItem> inventory = inventoryReader.getTestMap();
    private CateringSystem cateringSystem = new CateringSystem();
    private Cart cart = new Cart();
    private Map<String, Integer> cartMap = cart.getCart();
    private Money money = new Money();
    private LogFileWriter logFileWriter = new LogFileWriter();

    public CateringSystemCLI(Menu menu) {
        this.menu = menu;
    }

    /*
     * Your application starts here
     */
    public void run() {

        menu.showWelcomeMessage();

        while (true) {

            String mainMenuSelection;
            String orderMenuSelection;

            menu.showMainMenuOptions();

            mainMenuSelection = menu.getMainMenuUserSelection();

            if (mainMenuSelection.equals("1")) {
                menu.showItemsForSale(inventory);
            } else if (mainMenuSelection.equals("2")) {

                while (true) {

                    menu.showOrderMenuOptions(account.getAccountBalance());
                    orderMenuSelection = menu.getOrderMenuUserSelection();
                    boolean addingMoney = true;

                    if (orderMenuSelection.equals("1")) {

                        menu.showAddMoneyMenu();

                        while (addingMoney) {
                            try {
                                account.deposit(menu.getDepositAmount());
                            } catch (NumberFormatException e) {
                                addingMoney = false;
                            }
                        }


                    } else if (orderMenuSelection.equals("2")) {

                        while (true) {

                            menu.showProductsMenu(inventory);
                            String productCodeForPurchase = menu.getProductCodeForPurchase(inventory);
                            if (productCodeForPurchase.equals("EXIT")) {
                                break;
                            }
                            if (productCodeForPurchase.equals("")) {
                                continue;
                            }
                            boolean isItemSoldOut = cateringSystem.isItemSoldOut(inventory, productCodeForPurchase);
                            if (isItemSoldOut == true) {
                                menu.showSoldOutErrorMessage();
                                continue;
                            }
                            int quantityForPurchase = menu.getQuantityForPurchase();
                            if (quantityForPurchase == 0) {
                                continue;
                            }
                            if (quantityForPurchase < 0) {
                                menu.showNegativeNumberEnteredErrorMessage();
                                continue;
                            }

                            boolean isQuantityAvailableForPurchase = cateringSystem.isQuantityAvailable
                                    (quantityForPurchase, productCodeForPurchase, inventory);
                            if (isQuantityAvailableForPurchase == false) {
                                menu.showQuantityNotAvailable();
                                continue;
                            }

                            double totalCostOfItemPurchase = inventory.get(productCodeForPurchase).getProductPrice() * quantityForPurchase;
                            boolean isMoneyAdequateAvailableForPurchase = cateringSystem.isAdequateMoneyAvailableForPurchase(
                                    account.getAccountBalance(),
                                    totalCostOfItemPurchase);
                            if (isMoneyAdequateAvailableForPurchase == false) {
                                menu.showInsufficientAmountOfMoneyError();
                                continue;
                            }

                            cart.addItemToCart(productCodeForPurchase, quantityForPurchase);

                            cateringSystem.updateItemQuantity(inventory, quantityForPurchase, productCodeForPurchase);

                            account.updateAccountBalanceAfterPurchase(account, totalCostOfItemPurchase);

                            logFileWriter.logProductPurchase(quantityForPurchase, inventory.get(productCodeForPurchase).getProductName(), productCodeForPurchase, totalCostOfItemPurchase, account.getAccountBalance());

                            menu.showAddedToCartConformation(productCodeForPurchase, inventory, quantityForPurchase, account.getAccountBalance());

                        }
                    } else if (orderMenuSelection.equals("3")) {
                        String checkOutConfirmation = menu.getCheckOutConfirmation();
                        if (checkOutConfirmation.equals("Y")) {

                            menu.showCustomerReceipt(cartMap, inventory);
                            cart.clearCart(cartMap);
                            money.makeChange(account.getAccountBalance());
                            logFileWriter.logChangeGiven(account.getAccountBalance());
                            menu.showChangeGiven(money);
                            account.setAccountBalance(0d);
                            break;
                        }

                        continue;

                    } else if (orderMenuSelection.equals("4")) {
                        break;
                    } else {
                        menu.displayInvalidOptionError();
                        continue;
                    }
                }
            } else if (mainMenuSelection.equals("3")) {
                menu.thankYouMessage();
                break;
            } else {
                menu.displayInvalidOptionError();
            }

        }
    }

    /*
     * This starts the application, but you shouldn't need to change it.
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        CateringSystemCLI cli = new CateringSystemCLI(menu);
        cli.run();
    }
}
