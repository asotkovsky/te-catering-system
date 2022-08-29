package com.techelevator.filereader;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    This class should contain any and all details of access to the log file
 */
public class LogFileWriter {

    public void logAddMoney(double depositAmount, double accountBalance) {
        File logFile = new File("log.txt");
        try (FileWriter fileWriter = new FileWriter(logFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter);) {

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            String formattedBalance = currencyFormatter.format(accountBalance);

            String DATE_FORMATTER = "MM-dd-yyyy HH:mm:ss a";
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatDateTime = LocalDateTime.now().format(dateTimeFormatter);
            printWriter.println(formatDateTime + " ADD MONEY: " + depositAmount + " " + formattedBalance);
        } catch (IOException e) {
            System.out.println("Error, unable to write to file." + e.getMessage());
        }

    }

    public void logProductPurchase(int quantity, String productName, String productCode, double totalCost, double accountBalance) {
        File logFile = new File("log.txt");
        try (FileWriter fileWriter = new FileWriter(logFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter);) {

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            String formattedBalance = currencyFormatter.format(accountBalance);

            String DATE_FORMATTER = "MM-dd-yyyy HH:mm:ss a";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatDateTime = LocalDateTime.now().format(formatter);

            printWriter.println(formatDateTime + " " + quantity + " " + productName + " " + productCode + " " + totalCost + " " + formattedBalance);
        } catch (IOException e) {
            System.out.println("Error, unable to write to file." + e.getMessage());
        }

    }

    public void logChangeGiven(double accountBalance) {
        File logFile = new File("log.txt");
        try (FileWriter fileWriter = new FileWriter(logFile, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter);) {

            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            String formattedBalance = currencyFormatter.format(accountBalance);

            String DATE_FORMATTER = "MM-dd-yyyy HH:mm:ss a";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
            String formatDateTime = LocalDateTime.now().format(formatter);

            printWriter.println(formatDateTime + " GIVE CHANGE: " + formattedBalance + " $0.00");
        } catch (IOException e) {
            System.out.println("Error, unable to write to file." + e.getMessage());
        }


    }
}
