package CustomerRewards;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;

public class RewardApp {

  /**
   * Processes customer reward calculation by collecting user input, creating the appropriate
   * customer type, and generating a reward statement.
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
   * <p>The method uses {@link java.util.function.DoubleFunction} as a functional interface and
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
   *   <li>{@code 2} – Invalid input or runtime error
   * </ul>
   *
   * @throws IllegalArgumentException if an invalid customer type is entered
   * @see Customer
   * @see RegularCustomer
   * @see PremiumCustomer
   */
  public static void main(String[] args) {

    try {
      // Input Section
      Scanner sc = new Scanner(System.in);
      stmtPrinter.accept("Enter Customer type (1 for Regular, 2 for Premium): ");
      int customerTypeInput = sc.nextInt();
      stmtPrinter.accept("Enter Customer Name: ");
      String name = sc.next();
      Customer customer;
      // Customer Creation based on type
      if (customerTypeInput == 1) {
        customer = new RegularCustomer(name);
      } else if (customerTypeInput == 2) {
        customer = new PremiumCustomer(name);
      } else {
        stmtPrinter.accept("Invalid customer type. Defaulting to Regular Customer.");
        throw new IllegalArgumentException("Invalid customer type");
      }
      stmtPrinter.accept("Enter Purchase Amount: ");
      // purchase amount input
      double purchaseAmount = sc.nextDouble();

      // Reward Points Calculation
      DoubleFunction<Double> pointsCalculator = customer::calculatePoints;
      // Calculate base points
      double basePoints = pointsCalculator.apply(purchaseAmount);
      // Apply bonus multiplier to base points
      double finalPoints = Math.floor(bonusMultiplier.apply(basePoints) * 10) / 10;

      // Reward Statement Generation2
      stmtPrinter.accept("-------------------------------------");
      stmtPrinter.accept("Generating Reward Statement...!!!!!!!");
      stmtPrinter.accept("Customer Name : " + customer.getName());
      stmtPrinter.accept("Customer Type : " + customer.getType());
      stmtPrinter.accept("Purchase Amount  : $" + purchaseAmount);
      stmtPrinter.accept("Base Reward Points ::   " + basePoints);
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
  // Bonus multiplier function
  static Function<Double, Double> bonusMultiplier = points -> points * 1.10;
  // Statement printer using Consumer functional interface
  static Consumer<String> stmtPrinter = System.out::println;
}
