package week4CustomerRewards;

import java.util.function.BiFunction;

//import lombok.Getter;

/**
 * Abstract sealed class representing a Customer Implements Rewardable interface for reward point
 * calculation
 *
 * <p>This class serves as a base for different types of customers, enforcing a common structure and
 * behavior while allowing specific implementations in derived classes.
 *
 * @author Anvesh Hanmanthagari
 */
//@Getter
public abstract sealed class Customer implements Rewardable
    permits RegularCustomer, PremiumCustomer {
  // Common attributes for all customers
  protected String name;
  protected CustomerType type;
  /**
   * Constructor to initialize Customer with name and type
   *
   * @param name The name of the customer
   * @param type The type of the customer
   */
  public Customer(String name, CustomerType type) {
    this.name = name.toUpperCase();
    this.type = type;
  }

  /*
   * * BiFunction to calculate base points based on purchase amount and rate
   */

  static BiFunction<Double, Double, Double> calculateBasePoints =
      (purchaseAmount, rate) -> {
        if (purchaseAmount <= 0) {
          return 0.0;
        }
        return purchaseAmount * rate;
      };
}
