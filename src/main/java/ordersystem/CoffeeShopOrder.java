package ordersystem;

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
    double coffeePrice = 3.50;
    double teaPrice = 2.75;
    double cakePrice = 2.25;
    printStatement("Item Prices :");
    printStatement("Coffee: $" + coffeePrice);
    printStatement("Tea: $" + teaPrice);
    printStatement("Pastries: $" + cakePrice);
    printStatement("---------------------------");
    printStatement("Please enter your order details:");
    // for taking input from user
    Scanner scanner = new Scanner(System.in);
    printStatement("Enter number of Coffee: ");
    int numOfCoffeeOrder = parse(scanner);
    printStatement("Enter number of Tea: ");
    int numOfTeaOrder = parse(scanner);
    printStatement("Enter number of Pastries: ");
    int numOfPastriesOrder = parse(scanner);

    // calculations with arithmetic operators
    double subTotal =
        (numOfCoffeeOrder * coffeePrice)
            + (numOfTeaOrder * teaPrice)
            + (numOfPastriesOrder * cakePrice);

    // tax calculation with 7 percentage
    double tax = subTotal * (7.0 / 100.0);

    // total calculation
    double total = subTotal + tax;

    // printing the receipt with input
    printStatement("Coffees: " + numOfCoffeeOrder);
    printStatement("Teas: " + numOfTeaOrder);
    printStatement("Pastries: " + numOfPastriesOrder);
    printStatement("Subtotal: $" + String.format("%.2f", subTotal));
    printStatement("Tax: $" + String.format("%.2f", tax));
    printStatement("Total: $" + String.format("%.2f", total));

    // ternary operator to check if total is greater than 15
    if (total > 15) printStatement("You qualify for a free drink!");
    else printStatement("Add more items to qualify for a free drink!");

    // closing statements
    printStatement("Thank you for visiting the Coffee Shop!");
    printStatement("Cashier: Anvesh Hanmanthagari \nStudent ID: 1600540");
    printStatement("---------------------------");
    scanner.close();
    System.exit(0);
  }
  /*
   * Method to print statements
   */
  public static void printStatement(String printStatement) {
    System.out.println(printStatement);
  }

  /*
   * Method to parse integer input from user
   */
  public static int parse(Scanner inputOrder) {
    return Integer.parseInt(inputOrder.nextLine());
  }
}
