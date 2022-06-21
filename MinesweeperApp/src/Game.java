public class Game {
    
    private Grid grid = new Grid();

    public void start() {
        //This method will be the starting point of the game. It will need to ask the user to input the 
        //height and width of the grid, initialize the grid using the height and width, and call the gameLoop
        int height = Console.getInt("Enter the height of the grid: ", 4, 50);
        int width = Console.getInt("Enter the width of the grid: ", 4, 50);
        Grid.randomFillGrid(height, width);
        gameLoop();
    }

    private void gameLoop() {
        Cell[][] gameGrid = Grid.getGrid();
        boolean gameOver = false;
        while (gameOver == false) {
            System.out.println("Pick a spot to check for a mine.");
            int row = Console.getInt("Please enter the row number: ", 0, gameGrid.length);
            int column = Console.getInt("Please enter the column number: ", 0, gameGrid[0].length);
            System.out.println();
            gameOver = checkLocation(row, column);
            grid.displayGrid(gameGrid);
            if (gameOver == true) {
                if (gameGrid[row-1][column-1].isBomb()) {
                    System.out.println("Game Over!");
                    Grid.displayGridData(gameGrid);
                } else {
                    System.out.println("You Won!");
                }
            }
        }
    }

    public void numberOfMines(int row, int col) {
        Cell[][] gameGrid = Grid.getGrid();
        int count = 0;
        if((row > 0) && (col > 0) && (gameGrid[row-1][col-1].isBomb() == true)){ //up and left
            count++;
        }
        if(row > 0 && gameGrid[row-1][col].isBomb() == true){ //up
            count++;
        }
        if(row > 0 && col < gameGrid[row].length-1 && gameGrid[row-1][col+1].isBomb() == true){ // up and right
            count++;
        }
        if(col>0 && gameGrid[row][col-1].isBomb() == true){ //left
            count++;
        }
        if(col < gameGrid[row].length-1 && gameGrid[row][col+1].isBomb() == true){ //right
            count++;
        }
        if(row < gameGrid.length-1 && col > 0 && gameGrid[row+1][col-1].isBomb() == true){ //down and left
            count++;
        }
        if(row < gameGrid.length-1 && gameGrid[row+1][col].isBomb() == true){ //down
            count++;
        }
        if(row < gameGrid.length-1 && col < gameGrid[row].length-1 && gameGrid[row+1][col+1].isBomb() == true){//down and right
            count++;
        }
        gameGrid[row][col].setBombNearby(count);
              
    }

    public boolean checkLocation(int row, int col) {
        Cell[][] gameGrid = Grid.getGrid();
        if (gameGrid[row-1][col-1].isBomb()) {
            return true;
        } else {
            clearBlanks(row-1, col-1);
            return isGameWon();
        }
    }
    
    public void clearBlanks(int row, int col) {
        Cell[][] gameGrid = Grid.getGrid();
        numberOfMines(row, col);
        if (gameGrid[row][col].getBombNearby() > 0) {
            gameGrid[row][col].setRevealed(true);
        } else {
            gameGrid[row][col].setRevealed(true);
            if(isValidCellLocation(row - 1, col) && !gameGrid[row - 1][col].isRevealed()) {
                clearBlanks(row - 1, col);
            } if(isValidCellLocation(row + 1, col) && !gameGrid[row + 1][col].isRevealed()) {
                clearBlanks(row + 1, col);
            } if(isValidCellLocation(row, col - 1) && !gameGrid[row][col - 1].isRevealed()) {
                clearBlanks(row, col - 1);
            } if(isValidCellLocation(row, col + 1) && !gameGrid[row][col + 1].isRevealed()) {
                clearBlanks(row, col + 1);
            } if(isValidCellLocation(row + 1, col - 1) && !gameGrid[row + 1][col - 1].isRevealed()) {
                clearBlanks(row + 1, col - 1);
            } if(isValidCellLocation(row - 1, col + 1) && !gameGrid[row - 1][col + 1].isRevealed()) {
                clearBlanks(row - 1, col + 1);
            } if(isValidCellLocation(row + 1, col + 1) && !gameGrid[row + 1][col + 1].isRevealed()) {
                clearBlanks(row + 1, col + 1);
            } if(isValidCellLocation(row - 1, col - 1) && !gameGrid[row - 1][col - 1].isRevealed()) {
                clearBlanks(row - 1, col - 1);
            }
            
        }
    }
    
    public boolean isValidCellLocation(int row, int col) {
        Cell[][] gameGrid = Grid.getGrid();
        if (row < 0 || col < 0) return false;
        if(row >= gameGrid.length || col >= gameGrid[0].length) return false;
        return true;
    }
    
    public boolean isGameWon() {
        Cell[][] gameGrid = Grid.getGrid();
        int totalNumberOfCells = gameGrid.length * gameGrid[0].length;
        int count = 0;
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[i].length; j++) {
                if (gameGrid[i][j].isRevealed() || gameGrid[i][j].isBomb()) {
                    count++;
                }
            }
        }
        if (count == totalNumberOfCells) {
            return true;
        } else {
            return false;
        }
    }
}
