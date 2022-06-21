import java.util.Random;

public class Grid {
    
    private static final int BOMB_TOTAL = 10;
    private static Cell[][] grid;
    private static String introMessage = "There are a total of " + BOMB_TOTAL + " mines in the mine field.";

    public static Cell[][] randomFillGrid(int height, int width) {
 
        grid = new Cell[height][width];
        
        for (int j = 0; j < grid.length; j++) {
            for (int k = 0; k < grid[j].length; k++)
                grid[j][k] = new Cell(false);
        }

        Random random = new Random();
        int count = 0;
        while (count < BOMB_TOTAL) {
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            if (grid[randomX][randomY].isBomb()==false) {
                grid[randomX][randomY].setBomb(true);
                count++;
            }
        }
        displayGrid(grid);
       
        return grid;
    }
    
    public static void displayGrid(Cell[][] grid) {
        
        String output= "";
        output += introMessage + "\n";
        output += "\t";
        for (int i = 0; i < grid[0].length; i++) {
            output += (i+1) + " ";
        }
        output += "\n";
        


        for (int j = 0; j < grid.length; j++) {
            output += j+1 + "\t";
            for (int k = 0; k < grid[j].length; k++)
                if (grid[j][k].isRevealed() == false) {
                    output += "*" + " ";
                } else {
                    if (grid[j][k].getBombNearby() == 0) {
                        output += " " + " ";
                    } else {
                        output += grid[j][k].getBombNearby() + " ";
                    }  
                }

            output += "\n";
        }
        
        System.out.println(output);
    }
    
    public static void displayGridData(Cell[][] grid) {

        String output= "";
        output += introMessage + "\n";
        output += "\t";
        for (int i = 0; i < grid[0].length; i++) {
            output += (i+1) + " ";
        }
        output += "\n";
        
        for (int j = 0; j < grid.length; j++) {
            output += j+1 + "\t";
            for (int k = 0; k < grid[j].length; k++)
                if (grid[j][k].isBomb() == true) {
                    output += "*" + " ";
                } else {
                    if (grid[j][k].isRevealed()) {
                        if (grid[j][k].getBombNearby() == 0) {
                            output += " " + " ";
                        } else {
                            output += grid[j][k].getBombNearby() + " ";
                        }
                    } else {
                        output += "." + " ";
                    }
                }

            output += "\n";
        }
        System.out.println(output);
    }

    public static Cell[][] getGrid() {
        return grid;
    }



}
