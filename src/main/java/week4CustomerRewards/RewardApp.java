package week4CustomerRewards;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Predicate;

public class RewardApp {

  /**
   * Processes customer reward calculation by collecting user input, creating the appropriate
   * customer type, and generating a reward statement with bonus .
   *
   * <p>This method performs the following steps:
   *
   * <ul>
   *   <li>Accepts customer type and name as input
   *   <li>Creates a {@code RegularCustomer} or {@code PremiumCustomer} based on the selected type
   *   <li>Accepts purchase amount from the user
   *   <li>Calculates base reward points using polymorphism and a method reference
   *   <li>Applies a bonus multiplier using a lambda expression
   *   <li>Displays a detailed reward statement including date and time
   * </ul>
   *
   * <p>Method uses {@link java.util.function.DoubleFunction} as a functional interface and
   * demonstrates the use of method references and lambda expressions to simplify reward
   * calculations.
   *
   * <p>Error handling is implemented using a {@code try-catch} block. If invalid input is provided,
   * the program terminates with a non-zero system exit code.
   *
   * <p><b>Exit Codes:</b>
   *
   * <ul>
   *   <li>{@code 0} – Successful execution and statement generation
   *   <li>{@code 2} – Invalid input or runtime error and system exit
   * </ul>
   *
   * @throws IllegalArgumentException if an invalid customer type is entered
   * @see Customer
   * @see RegularCustomer for calculation of base points
   * @see PremiumCustomer for calculation of base points
   */
  public static void main(String[] args) {

    try {
      // Input Section
      Scanner sc = new Scanner(System.in);
      stmtPrinter.accept("Enter Customer type (1 for Regular, 2 for Premium): ");
      int customerTypeInput = sc.nextInt();
      stmtPrinter.accept("Enter Customer Name: ");
      sc.nextLine();
      String name = sc.nextLine();
      Customer customer;
      // Customer Creation based on type
      customer =
          checkCustomerRegularType.test(customerTypeInput)
              ? new RegularCustomer(name)
              : new PremiumCustomer(name);
      stmtPrinter.accept("Enter Purchase Amount: ");
      // purchase amount input
      double purchaseAmount = sc.nextDouble();

      // Reward Points Calculation
      DoubleFunction<Double> pointsCalculator = customer::calculatePoints;
      // Calculate base points
      double basePoints = pointsCalculator.apply(purchaseAmount);
      // Bonus percentage
      double bonusPercentage = 10;
      // Apply bonus multiplier to base points
      double finalPoints = Math.floor(bonusMultiplier.apply(basePoints, bonusPercentage) * 10) / 10;

      // Reward Statement Generation2
      stmtPrinter.accept("-------------------------------------");
      stmtPrinter.accept("Generating Reward Statement...!!!!!!!");
//      stmtPrinter.accept("Customer Name : " + customer.getName());
//      stmtPrinter.accept("Customer Type : " + customer.getType());
      stmtPrinter.accept("Purchase Amount  : $" + purchaseAmount);
      stmtPrinter.accept("Base Reward Points ::   " + basePoints);
      stmtPrinter.accept("Bonus Applied ::    " + bonusPercentage + "%");
      stmtPrinter.accept("Final Reward Points (After Bonus): " + finalPoints);
      stmtPrinter.accept("Date : " + java.time.LocalDate.now());
      stmtPrinter.accept("Time : " + java.time.LocalTime.now());
      stmtPrinter.accept("Statement Generated Successfully!!!!");
      stmtPrinter.accept("-------------------------------------");
      systemExit(0);
    } catch (Exception e) {
      stmtPrinter.accept("An error occurred: " + e.getMessage());
      systemExit(2);
    }
  }
  /*
   * Method to exit the application with a given status code.
   */
  public static void systemExit(int status) {
    try {
      stmtPrinter.accept("Exiting Application....!!!!!");
      Thread.sleep(1000); // Pause for 1 second before exiting to flush all logs
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }

    System.exit(status);
  }

  /** BiFunction to calculate final reward points after applying a bonus percentage. */
  static BiFunction<Double, Double, Double> bonusMultiplier =
      (points, percentage) -> points * (1 + percentage / 100);

  /**
   * for printing statements to the console.
   *
   * <p>This uses a method reference to {@link System#out println}, allowing any string passed to be
   * printed directly to the console.
   */
  static Consumer<String> stmtPrinter = System.out::println;

  /**
   * Predicate to check whether a customer is of type "Regular".
   *
   * <p>This uses a lambda expression to evaluate an integer value representing the customer type.
   * Returns {@code true} if the type equals 1 (Regular), otherwise returns {@code false}.
   */
  static Predicate<Integer> checkCustomerRegularType = (type) -> type == 1;
}
