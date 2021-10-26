package SmoothingAutomata;

public class Main {
    
    private static float WALL_CHANCE = 0.4f;
    private static int ITERATIONS = 8;
    private static int WIDTH = 100;
    private static int HEIGHT = 100;

    public static void main(String[] args) {
        int[][] array = Generate.generateArray(WIDTH, HEIGHT, WALL_CHANCE); 
        for (int i = 0; i <= ITERATIONS; i++) {
            array = Generate.smoothArray(array, i);
        }
        SharedUtils.Utils.saveImage(Generate.convertArray(array));
    }
}
