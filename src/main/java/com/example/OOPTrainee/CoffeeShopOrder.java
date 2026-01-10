package com.example.OOPTrainee;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeShopOrder {
  /*
   * Coffee Shop Order System
   * This program calculates the total cost of a coffee shop order including tax.
   * It prompts the user to enter the number of coffees, teas, and pastries they want to order,
   * then calculates the subtotal, tax, and total cost.
   * It also checks if the total exceeds a certain amount to qualify for a free drink.
   */

  public static void main(String[] args) {

    // prices of items
    printStatement("Welcome to the Coffee Shop!");
    Double CoffeePrice = 3.50;
    Double TeaPrice = 2.75;
    Double CakePrice = 2.25;
    printStatement("Item Prices :");
    printStatement("Coffee: $" + CoffeePrice);
    printStatement("Tea: $" + TeaPrice);
    printStatement("Pastries: $" + CakePrice);
    printStatement("---------------------------");
    printStatement("Please enter your order details:");
    // for taking input from user
    Scanner scanner = new Scanner(System.in);
    printStatement("Enter number of Coffee: ");
    Integer NumOfCoffeeOrder = Integer.valueOf(scanner.nextLine());
    printStatement("Enter number of Tea: ");
    Integer NumOfTeaOrder = Integer.valueOf(scanner.nextLine());
    printStatement("Enter number of Pastries: ");
    Integer NumOfPastriesOrder = Integer.valueOf(scanner.nextLine());

    // calculations with arithmetic operators
    Double subTotal =
        (NumOfCoffeeOrder * CoffeePrice)
            + (NumOfTeaOrder * TeaPrice)
            + (NumOfPastriesOrder * CakePrice);

    // tax calculation with 7 percentage
    Double tax = subTotal * 0.07;

    // total calculation
    Double total = subTotal + tax;

    // printing the receipt with input
    printStatement("Coffees: " + NumOfCoffeeOrder);
    printStatement("Teas: " + NumOfTeaOrder);
    printStatement("Pastries: " + NumOfPastriesOrder);
    printStatement("Subtotal: $" + String.format("%.2f", subTotal));
    printStatement("Tax: $" + String.format("%.2f", tax));
    printStatement("Total: $" + String.format("%.2f", total));

    // ternary operator to check if total is greater than 15
    if (total > 15) printStatement("You qualify for a free drink!");
    else printStatement("Add more items to qualify for a free drink!");

    // closing statements
    printStatement("Thank you for visiting the Coffee Shop!");
    printStatement("cashier: Anvesh H \n ID: 1600540");
    printStatement("---------------------------");
    scanner.close();
  }

  public static void printStatement(String printStatement) {
    System.out.println(printStatement);
  }
}
