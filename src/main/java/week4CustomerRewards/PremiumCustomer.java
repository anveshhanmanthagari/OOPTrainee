package week4CustomerRewards;

import static week4CustomerRewards.RewardApp.stmtPrinter;

public final class PremiumCustomer extends Customer {
  /**
   * Constructor to initialize PremiumCustomer with name and type
   *
   * @param name
   */
  public PremiumCustomer(String name) {
    super(name, CustomerType.PREMIUM);
  }
  /**
   * Method to calculate reward points for PremiumCustomer
   *
   * @param purchaseAmount The amount of purchase
   * @return Calculated reward points
   */
  @Override
  public double calculatePoints(double purchaseAmount) {
    stmtPrinter.accept("Calculating points from Premium Customer for amount : " + purchaseAmount);
    return calculateBasePoints.apply(purchaseAmount, 1.5);
  }
}
