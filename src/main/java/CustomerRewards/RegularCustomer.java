package CustomerRewards;

import static CustomerRewards.RewardApp.stmtPrinter;

public final class RegularCustomer extends Customer {
  /** Constructor to initialize RegularCustomer with name and type */
  public RegularCustomer(String name) {
    super(name, CustomerType.REGULAR);
  }
  /**
   * Method to calculate reward points for RegularCustomer
   *
   * @param purchaseAmount The amount of purchase
   * @return Calculated reward points
   */
  @Override
  public double calculatePoints(double purchaseAmount) {
    stmtPrinter.accept("Calculating points as Regular Customer for Amount: " + purchaseAmount);
    return purchaseAmount * 1.0;
  }
}
