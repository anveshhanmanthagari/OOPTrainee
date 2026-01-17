package creditcardpro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RewardsTracker {

  public static void main(String[] args) {
    Map<String, Double> purchaseList = new HashMap<>();

    Scanner sc = new Scanner(System.in);
    //For loop to get input in uppercase for items
    for (String item : Constant.categoriesList) {
      item = item.toUpperCase();
      printDetails("Enter your " + item + " expenses ");
      purchaseList.put(item, sc.nextDouble());
    }

    Double totalSpentAmount = 0.0;
    Double totalDemo = 0.0;

    //loop for adding all amount
    for (Map.Entry<String, Double> entry : purchaseList.entrySet()) {
      totalSpentAmount += entry.getValue();
      totalDemo += entry.getValue();
    }

    totalSpentAmount = Math.round(totalSpentAmount * 100.0) / 100.0;
    totalDemo = Math.round(totalDemo * 100.0) / 100.0;

    String tier;
    int tierAmount;
    // tier class and tierAmount for calculation
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

    printDetails("Enter Promo Code :");
    String promoCode = sc.next();
    int promoCodeInt = 0;
    switch (promoCode.toUpperCase()) {
      case "BONUS10":
        promoCodeInt = 10;
        break;
      case "TRAVEL5":
        promoCodeInt = 5;
        break;
      default:
        break;
    }

    statementSeparator();
    System.out.println("Equality Demo");
    statementSeparator();
    boolean equalsFunction = totalSpentAmount.equals(totalDemo);
    boolean equality_operator = (totalSpentAmount == totalDemo);
    printDetails("with using .equals function response :  " + equalsFunction);
    printDetails("with using ==  response :  " + equality_operator);
    statementSeparator();

    for (int i = 0; i < 3; i++) {
      printDetails("Calculating rewards..");
      System.out.println();
    }

    printDetails("=== CreditCardPro Monthly Summary ===");
    printDetails("Date: " + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    printDetails(
        "Next Billing: "
            + LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    statementSeparator();

    printDetails("Total Spending: $" + totalSpentAmount);
    printDetails("Tier: " + tier);

    int basePoints = (int) (totalSpentAmount * tierAmount);
    printDetails("Base Points: " + basePoints);

    int bonusPoints = (basePoints * promoCodeInt) / 100;
    printDetails("Bonus Points: " + bonusPoints);

    printDetails("Total Points Earned: " + (basePoints + bonusPoints));

    printDetails("Promo Code: " + promoCode);

    printDetails("Great work! Keep building your rewards");
  }
/*

 */
  public static void printDetails(String printStatement) {
    System.out.println(printStatement);
  }

  public static void statementSeparator() {
    printDetails("-----------------------------------------");
  }


}
