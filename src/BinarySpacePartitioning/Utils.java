package BinarySpacePartitioning;

import java.util.Random;

public class Utils {

  static final float RATIO = 0.45f;
  static final String WALL = "▓";
  static final String OPEN = "░";

  public static final Random random = new Random();

  private Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static int randomInRange(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }

  public static String repeat(int count, String with) {
    return new String(new char[count]).replace("\0", with);
  }
}
