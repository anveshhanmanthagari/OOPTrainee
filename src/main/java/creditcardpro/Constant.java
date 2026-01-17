package creditcardpro;

import java.util.List;

public final class Constant {
  private Constant() {
    // private constructor to prevent instantiation
  }

  public static String GROCERY = "grocery";
  public static String DINNING = "dinning";
  public static String TRAVEL = "travel";
  public static String GAS = "gas";
  public static String OTHER = "other";
  static List<String> categoriesList = List.of(GROCERY, DINNING, TRAVEL, GAS, OTHER);
}
