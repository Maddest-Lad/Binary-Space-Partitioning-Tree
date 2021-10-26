package SmoothingAutomata;

import static SharedUtils.Utils.WALL;
import static SharedUtils.Utils.OPEN;

public class Generate {

    private Generate() {
        throw new IllegalStateException("Utility class");
    }

    public static int[][] generateArray(int height, int width, float chance) {
        int[][] automata = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                automata[i][j] = (chance < Math.random()) ? 1 : 0;
            }
        }
        return automata;
    }

    public static int getNeighbors(int[][] automata, int i, int j) {
        int neighbors = 0;
        
        for(int x = i - 1; x <= i + 1; x++) {
            for(int y = j - 1; y <= j + 1; y++) {
                if(x == i && y == j) {
                    continue;
                }
                // Count Edge Cells For Solid Border
                if(x < 0 || x >= automata.length || y < 0 || y >= automata[0].length) {
                    neighbors++;
                    continue;
                }
                neighbors += automata[x][y];
            }
        }
        return neighbors;
    }

    public static int getNeighborsNAway(int[][] automata, int i, int j, int distance) {
        int neighbors = 0;
        
        for(int x = i - distance; x <= i + distance; x++) {
            for(int y = j - distance; y <= j + distance; y++) {
                if(x == i && y == j) {
                    continue;
                }
                // Count Edge Cells For Solid Border
                if(x < 0 || x >= automata.length || y < 0 || y >= automata[0].length) {
                    neighbors++;
                    continue;
                }
                neighbors += automata[x][y];
            }
        }
        return neighbors;
    }

    public static int[][] smoothArray(int[][] automata, int generation) {
        int[][] newAutomata = new int[automata.length][automata[0].length];
        for (int i = 0; i < automata.length; i++) {
            for (int j = 0; j < automata[0].length; j++) {

                int neighbors = getNeighbors(automata, i, j);
                int farNeighbors = getNeighborsNAway(automata, i, j, 2);
                
                if(generation < 5) {
                    newAutomata[i][j] = (neighbors >= 5 || farNeighbors <= 7) ? 1 : 0;
                } else {
                    newAutomata[i][j] = (neighbors >= 5) ? 1 : 0;
                }
            }
        }

        // Keep Borders Intact
        if(generation < 5) {
            // Set Top and Bottom to 1
            for(int i = 0; i < automata.length; i++) {
                newAutomata[i][0] = 1;
                newAutomata[i][automata[0].length - 1] = 1;
            }

            // Set Left and Right to 1
            for(int j = 0; j < automata[0].length; j++) {
                newAutomata[0][j] = 1;
                newAutomata[automata.length - 1][j] = 1;
            }
        }

        return newAutomata;
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] == 1 ? OPEN : WALL);
            }
            System.out.println();
        }
    }

    public static String[][] convertArray(int[][] array) {
        String[][] newArray = new String[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                newArray[i][j] = (array[i][j] == 0) ? OPEN : WALL;
            }
        }
        return newArray;
    }
}

