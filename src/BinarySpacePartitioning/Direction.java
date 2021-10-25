package BinarySpacePartitioning;

public enum Direction {
  HORIZONTAL,
  VERTICAL;

  public static Direction getRandomDirection() {
    return values()[(int) (Math.random() * values().length)];
  }
}
