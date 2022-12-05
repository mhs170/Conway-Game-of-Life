package conwaygame;
import java.io.ObjectInputStream.GetField;
import java.nio.file.attribute.GroupPrincipal;
import java.security.DrbgParameters.Capability;
import java.util.ArrayList;

import javax.swing.text.html.StyleSheet;
import javax.xml.parsers.FactoryConfigurationError;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
        
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {
        // WRITE YOUR CODE HERE
        StdIn.setFile(file);
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        grid = new boolean[r][c];
        for (int i = 0; i < r; i++){
            for (int j = 0; j < c ; j++){
                boolean isAlive = StdIn.readBoolean();
                if (isAlive == false){
                    grid[i][j] = false;
                }
                if (isAlive == true){
                    grid[i][j] = true;
                }
            }
        }
    }
        
    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**`
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE

        if (grid[row][col] == ALIVE){
            return true;
        }

        return false; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE

        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++){
                if (grid[row][col] == ALIVE){
                    return true;
                }
            }
        }
        return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {

        // WRITE YOUR CODE HERE


        
        int count = 0;

        //System.out.println("numOfRows: " + grid.length);
        //System.out.println("numOfCols: " + grid[0].length);
        //1
        if (row == 0 && col == 0){
            if (getCellState(0, 1) == true){
                count += 1;
            }
            if (getCellState(1, 0) == true){
                count += 1;
            }
            if (getCellState(1, 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, 0) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, 1) == true){
                count += 1;
            }
            if (getCellState(0, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(1, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                count += 1;
            }
        }
        //2
        else if (row == 0 && col == grid[0].length - 1){
            if (getCellState(0, 0) == true){
                count += 1;
            }
            if (getCellState(0, col - 1) == true){
                count += 1;
            }
            if (getCellState(1, 0) == true){
                count += 1;
            }
            if (getCellState(row + 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(1, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, grid[0].length - 2) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, 0) == true){
                count += 1;
            }
        }
        //3
        else if (row == grid.length - 1 && col == 0){
            if (getCellState(0, col) == true){
                count += 1;
            }
            if (getCellState(row - 1, col) == true){
                count += 1;
            }
            if (getCellState(0, col + 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(row, col + 1) == true){
                count += 1;
            }
            if (getCellState(0, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                count += 1;
            }
        }
        //4
        else if (row == grid.length - 1 && col == grid[0].length - 1){
            if (getCellState(0, 0) == true){
                count += 1;
            }
            if (getCellState(grid.length - 2, 0) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, 0) == true){
                count += 1;
            }
            if (getCellState(0, grid[0].length - 2) == true){
                count += 1;
            }
            if (getCellState(grid.length - 2, grid[0].length - 2) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, grid[0].length - 2) == true){
                count += 1;
            }
            if (getCellState(0, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 2, grid[0].length - 1) == true){
                count += 1;
            }
        }
        //5
        else if (col == grid[0].length - 1 && (row != 0 || row != grid.length - 1)){
            if (getCellState(row - 1, col) == true){
                count += 1;
            }
            if (getCellState(row + 1, col) == true){
                count += 1;
            }
            if (getCellState(row - 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(row, col - 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, col - 1) == true){
                count += 1;
            }
            // might have to change these
            if (getCellState(row, 0) == true){
                count += 1;
            }
            if (getCellState(row - 1, 0) == true){
                count += 1;
            }
            if (getCellState(row + 1, 0) == true){
                count += 1;
            }
        }
        //6
        else if (row == 0 && (col != 0 || col != grid[0].length - 1)){
            if (getCellState(row, col - 1) == true){
                count += 1;
            }
            if (getCellState(row, col + 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, col) == true){
                count += 1;
            }
            if (getCellState(row + 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, col) == true){
                count += 1;
            }
            if (getCellState(grid.length - 1, col + 1) == true){
                count += 1;
            }
        }
        //7
        else if (col == 0 && (row != 0 || row != grid.length)){
            if (getCellState(row - 1, col) == true){
                count += 1;
            }
            if (getCellState(row + 1, col) == true){
                count += 1;
            }
            if (getCellState(row - 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(row, col + 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(row, grid[0].length - 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, grid[0].length - 1) == true){
                count += 1;
            }
        }
        //8
        else if (row == grid.length - 1 && (col != 0 || col != grid[0].length - 1)){
            if (getCellState(row, col - 1) == true){
                count += 1;
            }
            if (getCellState(row, col + 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, col) == true){
                count += 1;
            }
            if (getCellState(row - 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(0, col - 1) == true){
                count += 1;
            }
            if (getCellState(0, col) == true){
                count += 1;
            }
            if (getCellState(0, col + 1) == true){
                count += 1;
            }
        }
        //9
        else{
            if (getCellState(row + 1, col) == true){
                count += 1;
            }
            if (getCellState(row - 1, col) == true){
                count += 1;         
            }
            if (getCellState(row, col + 1) == true){
                count += 1;
            }
            if (getCellState(row, col - 1) == true){
                count += 1;        
            }
            if (getCellState(row + 1, col + 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(row + 1, col - 1) == true){
                count += 1;
            }
            if (getCellState(row - 1, col + 1) == true){
                count += 1;
            }
        }
        return count; // update this line, provided so that code compiles
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        // WRITE YOUR CODE HERE

        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (getCellState(i, j) == true){
                    if (numOfAliveNeighbors(i, j) == 2 || numOfAliveNeighbors(i, j) == 3){
                        newGrid[i][j] = true;
                    }
                    else if (numOfAliveNeighbors(i, j) == 1 || numOfAliveNeighbors(i, j) == 0 || numOfAliveNeighbors(i, j) >= 4){
                        newGrid[i][j] = false;        
                    }
                }
                if (getCellState(i, j) == false){
                    if (numOfAliveNeighbors(i, j) == 3){
                        newGrid[i][j] = true;
                    }
                    else{
                        newGrid[i][j] = false;
                    }
                }
            }
        }
        return newGrid; // update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE

        grid = computeNewGrid();
        totalAliveCells = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (getCellState(i, j) == true){
                    totalAliveCells += 1;
                }
            }
        }
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        // WRITE YOUR CODE HERE
        for (int i = 0; i < n; i++){
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        int row = grid.length;
        int col = grid[0].length;
        WeightedQuickUnionUF myObj = new WeightedQuickUnionUF(row, col);
        
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                
                if (getCellState(i, j) == true){
                    //1
                    if (i == 0 && j == 0){
                        if (getCellState(0, 1) == true){
                            myObj.union(i, j, 0, 1);
                        }
                        if (getCellState(1, 0) == true){
                            myObj.union(i, j, 1, 0);
                        }
                        if (getCellState(1, 1) == true){
                            myObj.union(i, j, 1, 1);
                        }
                        if (getCellState(grid.length - 1, 0) == true){
                            myObj.union(i, j, grid.length - 1, 0);
                        }
                        if (getCellState(grid.length - 1, 1) == true){
                            myObj.union(i, j, grid.length - 1, 1);
                        }
                        if (getCellState(0, grid[0].length - 1) == true){
                            myObj.union(i, j, 0, grid[0].length - 1);
                        }
                        if (getCellState(1, grid[0].length - 1) == true){
                            myObj.union(i, j, 1, grid[0].length - 1);
                        }
                        if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                            myObj.union(i, j, grid.length - 1, grid[0].length - 1);
                        }
                    }
                    //2
                    else if (i == 0 && j == grid[0].length - 1){
                        if (getCellState(0, 0) == true){
                            myObj.union(i, j, 0, 0);
                        }
                        if (getCellState(0, j - 1) == true){
                            myObj.union(i, j, 0, j - 1);
                        }
                        if (getCellState(1, 0) == true){
                            myObj.union(i, j, 1, 0);
                        }
                        if (getCellState(i + 1, j - 1) == true){
                            myObj.union(i, j, i + 1, j - 1);
                        }
                        if (getCellState(1, grid[0].length - 1) == true){
                            myObj.union(i, j, 1, grid[0].length - 1);
                        }
                        if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                            myObj.union(i, j, grid.length - 1, grid[0].length - 1);
                        }
                        if (getCellState(grid.length - 1, grid[0].length - 2) == true){
                            myObj.union(i, j, grid.length - 1, grid[0].length - 2);
                        }
                        if (getCellState(grid.length - 1, 0) == true){
                            myObj.union(i, j, grid.length - 1, 0);
                        }
                    }
                    //3
                    else if (i == grid.length - 1 && j == 0){
                        if (getCellState(0, 0) == true){
                            myObj.union(i, j, 0, 0);
                        }
                        if (getCellState(i - 1, 0) == true){
                            myObj.union(i, j, i - 1, 0);
                        }
                        if (getCellState(0, 1) == true){
                            myObj.union(i, j, 0, 1);
                        }
                        if (getCellState(i - 1, 1) == true){
                            myObj.union(i, j, i - 1, 1);
                        }
                        if (getCellState(i, 1) == true){
                            myObj.union(i, j, i, 1);
                        }
                        if (getCellState(0, grid[0].length - 1) == true){
                            myObj.union(i, j, 0, grid[0].length - 1);
                        }
                        if (getCellState(row - 1, grid[0].length - 1) == true){
                            myObj.union(i, j, row - 1, grid[0].length - 1);
                        }
                        if (getCellState(grid.length - 1, grid[0].length - 1) == true){
                            myObj.union(i, j, grid.length - 1, grid[0].length - 1);
                        }
                    }
                    //4
                    else if (i == grid.length - 1 && j == grid[0].length - 1){
                        if (getCellState(0, 0) == true){
                            myObj.union(i, j, 0, 0);
                        }
                        if (getCellState(grid.length - 2, 0) == true){
                            myObj.union(i, j, grid.length - 2, 0);
                        }
                        if (getCellState(grid.length - 1, 0) == true){
                            myObj.union(i, j, grid.length - 1, 0);
                        }
                        if (getCellState(0, grid[0].length - 2) == true){
                            myObj.union(i, j, 0, grid[0].length - 2);
                        }
                        if (getCellState(grid.length - 2, grid[0].length - 2) == true){
                            myObj.union(i, j, grid.length - 2, grid[0].length - 2);
                        }
                        if (getCellState(grid.length - 1, grid[0].length - 2) == true){
                            myObj.union(i, j, grid.length - 1, grid[0].length - 2);
                        }
                        if (getCellState(0, j) == true){
                            myObj.union(i, j, 0, j);
                        }
                        if (getCellState(grid.length - 2, grid[0].length - 1) == true){
                            myObj.union(i, j, grid.length - 2, grid[0].length - 1);
                        }
                    }
                    //5
                    else if (j == grid[0].length - 1 && (i != 0 || i != grid.length - 1)){
                        if (getCellState(i - 1, j) == true){
                            myObj.union(i, j, i - 1, j);
                        }
                        if (getCellState(i + 1, j) == true){
                            myObj.union(i, j, i + 1, j);
                        }
                        if (getCellState(i - 1, j - 1) == true){
                            myObj.union(i, j, i - 1, j - 1);
                        }
                        if (getCellState(i, j - 1) == true){
                            myObj.union(i, j, i, j - 1);
                        }
                        if (getCellState(i + 1, j - 1) == true){
                            myObj.union(i, j, i + 1, j - 1);
                        }
                        // might have to change these
                        if (getCellState(i, 0) == true){
                            myObj.union(i, j, i, 0);
                        }
                        if (getCellState(i - 1, 0) == true){
                            myObj.union(i, j, i - 1, 0);
                        }
                        if (getCellState(i + 1, 0) == true){
                            myObj.union(i, j, i + 1, 0);
                        }
                    }
                    //6
                    else if (i == 0 && (j != 0 || j != grid[0].length - 1)){
                        if (getCellState(i, j - 1) == true){
                            myObj.union(i, j, i, j - 1);
                        }
                        if (getCellState(i, j + 1) == true){
                            myObj.union(i, j, i, j + 1);
                        }
                        if (getCellState(i + 1, j - 1) == true){
                            myObj.union(i, j, i + 1, j - 1);
                        }
                        if (getCellState(i + 1, j) == true){
                            myObj.union(i, j, i + 1, j);
                        }
                        if (getCellState(i + 1, j + 1) == true){
                            myObj.union(i, j, i + 1, j + 1);
                        }
                        if (getCellState(grid.length - 1, j - 1) == true){
                            myObj.union(i, j, grid.length - 1, j - 1);
                        }
                        if (getCellState(grid.length - 1, j) == true){
                            myObj.union(i, j, grid.length - 1, j);
                        }
                        if (getCellState(grid.length - 1, j + 1) == true){
                            myObj.union(i, j, grid.length - 1, j + 1);
                        }
                    }
                    //7
                    else if (j == 0 && (i != 0 || i != grid.length)){
                        if (getCellState(i - 1, j) == true){
                            myObj.union(i, j, i - 1, j);
                        }
                        if (getCellState(i + 1, j) == true){
                            myObj.union(i, j, i + 1, j);
                        }
                        if (getCellState(i - 1, j + 1) == true){
                            myObj.union(i, j, i - 1, j + 1);
                        }
                        if (getCellState(i, j + 1) == true){
                            myObj.union(i, j, i, j + 1);
                        }
                        if (getCellState(i + 1, j + 1) == true){
                            myObj.union(i, j, i + 1, j + 1);
                        }
                        if (getCellState(i - 1, grid[0].length - 1) == true){
                            myObj.union(i, j, i - 1, grid[0].length -1);
                        }
                        if (getCellState(i, grid[0].length - 1) == true){
                            myObj.union(i, j, i, grid[0].length - 1);
                        }
                        if (getCellState(i + 1, grid[0].length - 1) == true){
                            myObj.union(i, j, i + 1, grid[0].length - 1);
                        }
                    }
                    //8
                    else if (i == grid.length - 1 && (j != 0 || j != grid[0].length - 1)){
                        if (getCellState(i, j - 1) == true){
                            myObj.union(i, j, i, j - 1);
                        }
                        if (getCellState(i, j + 1) == true){
                            myObj.union(i, j, i, j + 1);
                        }
                        if (getCellState(i - 1, j) == true){
                            myObj.union(i, j, i - 1, j);
                        }
                        if (getCellState(i - 1, j + 1) == true){
                            myObj.union(i, j, i - 1, j + 1);
                        }
                        if (getCellState(i - 1, j - 1) == true){
                            myObj.union(i, j, i - 1, j - 1);
                        }
                        if (getCellState(0, j - 1) == true){
                            myObj.union(i, j, 0, j - 1);
                        }
                        if (getCellState(0, j) == true){
                            myObj.union(i, j, 0, j);
                        }
                        if (getCellState(0, j + 1) == true){
                            myObj.union(i, j, 0, j + 1);
                        }
                    }
                    //9
                    else{
                        if (getCellState(i + 1, j) == true){
                            myObj.union(i, j, i + 1, j);
                        }
                        if (getCellState(i - 1, j) == true){
                            myObj.union(i, j, i - 1, j);         
                        }
                        if (getCellState(i, j + 1) == true){
                            myObj.union(i, j, i, j + 1);
                        }
                        if (getCellState(i, j - 1) == true){
                            myObj.union(i, j, i, j - 1);
                        }
                        if (getCellState(i + 1, j + 1) == true){
                            myObj.union(i, j, i + 1, j + 1);
                        }
                        if (getCellState(i - 1, j - 1) == true){
                            myObj.union(i, j, i - 1, j - 1);
                        }
                        if (getCellState(i + 1, j - 1) == true){
                            myObj.union(i, j, i + 1, j - 1);
                        }
                        if (getCellState(i - 1, j + 1) == true){
                            myObj.union(i, j, i - 1, j + 1);
                        } 
                    }
                }
            }
        }
        
        ArrayList<Integer> arr = new ArrayList<>();
        
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (getCellState(i, j) == true){
                    int root = myObj.find(i, j);
                    if (!arr.contains(root)){
                        arr.add(root);
                    }
                }
            }
        }
        /*System.out.println(arr);
        int count = 1;
        for (int i = 1; i < arr.size(); i++){
            for (int j = 0; j < i; j++){
                if (isAlive() == false){
                    count = 0;
                }
                else if (isAlive() == true){
                    if (arr.indexOf(i) == arr.indexOf(j)){
                        break;
                    }
                    if (i == j){
                        count += 1;
                    }
                }
            }
        }*/
        System.out.println(arr);
        return arr.size(); // update this line, provided so that code compiles
    }
}