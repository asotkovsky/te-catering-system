package com.techelevator.filereader;

import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private String inventoryFileName;

    public InventoryFileReader(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;
    }
    public InventoryFileReader(){}

    public Map<String, CateringItem> readInventory() throws FileNotFoundException {
        Map<String, CateringItem> items = new TreeMap<>();

        File file = new File(inventoryFileName);

        try(Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                CateringItem itemPulledFromFile = buildItemFromLine(line);
                items.put(itemPulledFromFile.getProductCode(), itemPulledFromFile);
            }
        }
        return items;
    }

    private CateringItem buildItemFromLine(String line){
        CateringItem item = null;
        String[] parts = line.split("\\|");

        String productType = parts[0];
        String productCode = parts[1];
        String productName = parts[2];
        String productPrice = parts[3];


        if(productType.equalsIgnoreCase("B")){
            item = new Beverage(productCode, productName);
        } else if(productType.equalsIgnoreCase("A")){
            item = new Appetizer(productCode, productName);
        } else if(productType.equalsIgnoreCase("E")){
            item = new Entree(productCode, productName);
        } else if(productType.equalsIgnoreCase("D")){
            item = new Dessert(productCode, productName);
        }

        item.setProductPrice(Double.parseDouble(productPrice));

        return item;
    }

    // This code allows us to grab a test Map
    public Map<String, CateringItem> getTestMap() {
        Map<String, CateringItem> testMap = new TreeMap<>();
        try {
            InventoryFileReader test = new InventoryFileReader("Testcateringsystem.csv");
            testMap = test.readInventory();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return testMap;
    }


}
