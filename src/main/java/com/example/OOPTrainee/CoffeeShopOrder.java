package com.example.OOPTrainee;

import java.util.Scanner;

public class CoffeeShopOrder {
  /*
     This program calculates the total cost of a coffee shop order including tax.
     It prompts the user to input the number of coffees, teas, and pastries they want to order.
     It then calculates the subtotal, tax, and total amount due.
     If the total amount exceeds $15, it informs the user that they qualify for a free drink.

  */

  public static void main(String[] args) {
    // for taking input from user
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter number of Coffee: ");
    Integer NumOfCoffeeOrder = Integer.valueOf(scanner.nextLine());
    System.out.println("Enter number of Tea: ");
    Integer NumOfTeaOrder = Integer.valueOf(scanner.nextLine());
    System.out.println("Enter number of Cake: ");
    Integer NumOfPastriesOrder = Integer.valueOf(scanner.nextLine());
    // prices of items
    Double CoffeePrice = 3.50;
    Double TeaPrice = 2.75;
    Double CakePrice = 2.25;

    // calculations with arithmetic operators
    double subTotal =
        (NumOfCoffeeOrder * CoffeePrice)
            + (NumOfTeaOrder * TeaPrice)
            + (NumOfPastriesOrder * CakePrice);
    // tax calculation with 7 percentage
    double tax = subTotal * 0.07;
    // total calculation
    double total = subTotal + tax;
    // printing the receipt with input
    System.out.println("Coffees: " + NumOfCoffeeOrder);
    System.out.println("Teas: " + NumOfTeaOrder);
    System.out.println("Pastries: " + NumOfPastriesOrder);
    System.out.println("Subtotal: $" + String.format("%.2f", subTotal));
    System.out.println("Tax: $" + String.format("%.2f", tax));
    System.out.println("Total: $" + String.format("%.2f", total));
    // ternary operator to check if total is greater than 15
    if (total > 15) System.out.println("You qualify for a free drink!");
    else System.out.println("Add more items to qualify for a free drink!");
  }
}
