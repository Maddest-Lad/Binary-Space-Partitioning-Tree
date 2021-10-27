package SharedUtils;

import java.awt.*;
import java.util.Random;

public class Utils {

  public static final float RATIO = 0.45f;
  public static final String WALL = "▓";
  public static final String OPEN = "░";

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

  public static void saveImage(String[][] arr) {
    SimpleImage image = new SimpleImage(arr.length, arr[0].length);
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[0].length; j++) {
        if (arr[i][j].equals(Utils.OPEN)) {
          image.set(i, j, Color.black);
        } else {
          image.set(i, j, Color.green);
        }
      }
    }
    image.save("current.png");
  }
}
