package CustomerRewards;
/**
 * Represents a functional interface for calculating reward points based on a customer's purchase
 * amount.
 *
 * <p>This interface defines a single abstract method, making it a {@link FunctionalInterface}. It
 * is intended to support lambda expressions and method references.
 *
 * <p>Different customer types can provide their own implementation of reward calculation logic
 * while allowing the calling code to remain independent of the specific implementation.
 *
 * @author Anvesh Hanmanthagari
 */
@FunctionalInterface
public interface Rewardable {
  double calculatePoints(double purchaseAmount);
}
