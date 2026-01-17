package creditcardpro;

import java.util.List;

public class Constant {
  private Constant() {
    // private constructor to prevent instantiation
  }

  public static final String GROCERY = "grocery";
  public static final String DINNING = "dinning";
  public static final String TRAVEL = "travel";
  public static final String GAS = "gas";
  public static final String OTHER = "other";
  public static final String BONUS10 = "BONUS10";
  public static final String TRAVEL5 = "TRAVEL5";
  public static final List<String> categoriesList = List.of(GROCERY, DINNING, TRAVEL, GAS, OTHER);
}
