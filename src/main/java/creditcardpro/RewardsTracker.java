package creditcardpro;

import static creditcardpro.Constant.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RewardsTracker {
  public static String promoCode;

  public static void main(String[] args) {
    Map<String, Double> purchaseList = new HashMap<>();

    Scanner sc = new Scanner(System.in);
    // loop for taking input from user for each category
    for (String item : Constant.categoriesList) {
      item = item.toUpperCase();
      printDetails("Enter your " + item + " expenses ");
      purchaseList.put(item, sc.nextDouble());
    }

    Double totalSpentAmount = 0.0;
    Double totalDemo = 0.0;

    // loop to calculate total spent amount and total demo for equality check
    for (Map.Entry<String, Double> entry : purchaseList.entrySet()) {
      totalSpentAmount += entry.getValue();
      totalDemo += entry.getValue();
    }

    totalSpentAmount = Math.round(totalSpentAmount * 100.0) / 100.0;
    totalDemo = Math.round(totalDemo * 100.0) / 100.0;

    // Determine the tier based on total spent amount
    String tier;
    int tierAmount;

    if (totalSpentAmount >= 500) {
      tier = "Platinum (x3 points)";
      tierAmount = 3;
    } else if (totalSpentAmount >= 250) {
      tier = "Gold (x2 points)";
      tierAmount = 2;
    } else {
      tier = "Silver (x1 points)";
      tierAmount = 1;
    }
    printDetails("Total Spent Amount: $" + totalSpentAmount);
    printDetails("Tier Details : " + tier + " Tier Multiplier : " + tierAmount);
    // Prompt user for promo code and determine bonus points based on the promo code entered
    printDetails("Enter Promo Code :");
    int promoCodeInt = bonusPoints();
    // Print the equality check results for total spent amount and total demo using both .equals
    // function and == operator
    statementSeparator();
    System.out.println("Equality Demo");
    statementSeparator();
    boolean equalsFunction = totalSpentAmount.equals(totalDemo);
    // Use Double.compare to compare numeric values safely instead of using '==' on Number objects
    boolean equality_operator = totalSpentAmount == totalDemo;
    printDetails("with using .equals function response :  " + equalsFunction);
    printDetails("with using ==  response :  " + equality_operator);
    statementSeparator();
    // Loop to print calculating rewards statement 3 times with a delay of 1 second between each
    // print statement
    for (int i = 0; i < 3; i++) {
      printDetails("Calculating rewards..");
      System.out.println();
    }
    // Print the monthly summary statement with the total spent amount, tier, base points, bonus
    // points, total points earned, promo code and a closing statement
    statementSeparator();
    printDetails("=== CreditCardPro Monthly Summary ===");
    printDetails("Date : " + LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
    printDetails(
        "Next Billing: "
            + LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
    statementSeparator();

    printDetails("Total Spending: $" + totalSpentAmount);
    printDetails("Tier : " + tier);

    int basePoints = (int) (totalSpentAmount * tierAmount);
    printDetails("Base Points : " + basePoints);

    int bonusPoints = (basePoints * promoCodeInt) / 100;
    printDetails("Bonus Points : " + bonusPoints);

    printDetails("Total Points Earned : " + (basePoints + bonusPoints));

    printDetails("Promo Code : " + promoCode);

    printDetails("Great work! Keep building your rewards");
  }
  /*
   * Method to print statements
   */
  public static void printDetails(String printStatement) {
    System.out.println(printStatement);
  }
  /*
   * Method to print statement separator
   */
  public static void statementSeparator() {
    printDetails("-----------------------------------------");
  }

  public static int bonusPoints() {
    Scanner sc = new Scanner(System.in);
    String promoCo = sc.next();
    promoCode = promoCo.toUpperCase();
    int promoCodeInt;
    switch (promoCode) {
      case BONUS10:
        promoCodeInt = 10;
        break;
      case TRAVEL5:
        promoCodeInt = 5;
        break;
      default:
        promoCodeInt = 0;
        promoCode = "Invalid Promo Code";
        break;
    }

    return promoCodeInt;
  }
}
