/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, Board.java
 */
package battleship;

import java.io.BufferedReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.IOException;

/**
 * The class to represent the grid of cells (squares).
 * A collection of ships is also kept so the Board
 * can be asked if the game is over.
 * The class is Serializable so that its instance can
 * be saved to a file in binary form using an
 * {@link java.io.ObjectOutputStream} and restored
 * with an {@link java.io.ObjectInputStream}.
 * Because the object holds references to all other
 * objects in the system, no other objects need to
 * be separately saved.
 */
public class Board implements Serializable {

    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private Cell[][] board;
    private int height = 0;
    private int width = 0;

    /**
     * creates the board
     * @param height int resembling # of rows on board
     * @param width int resembling # of columns on board
     */
    public Board(int height, int width){
        this.board = new Cell[height][width];
        this.height = height;
        this.width = width;

        for(int idx = 0; idx < getHeight(); idx++){
            for(int idx2 = 0; idx2 < getWidth(); idx2++){
                board[idx][idx2] = new Cell(idx, idx2);
            }
        }
    }

    public void loadBoard(BufferedReader br)throws OverlapException, OutOfBoundsException{
        String line;
        try{
            while((line = br.readLine()) != null){
                String[] currLine = line.split(" ");
                int row = Integer.parseInt(currLine[0]);
                int col = Integer.parseInt(currLine[1]);
                Ship.Orientation orientation = (currLine[2].equals("HORIZONTAL")) ?
                        Ship.Orientation.HORIZONTAL : Ship.Orientation.VERTICAL;
                int length = Integer.parseInt(currLine[3]);
                int lcol = (orientation == Ship.Orientation.HORIZONTAL) ?
                        (col + length)-1 : col;
                Ship ship = new Ship(this, row, lcol, orientation, length);
                addShip(ship);

                if(orientation != null && orientation == Ship.Orientation.HORIZONTAL){
                    for(int currCol = col; currCol <= lcol; currCol++){
                        board[row][currCol].putShip(ship);
                    }
                }else{
                    for(int currRow = row; currRow <= (row+length)-1; currRow++){
                        board[currRow][col].putShip(ship);
                    }
                }
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    /**
     * returns the height of the board
     * @return int
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * returns the width of the board
     * @return int
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Fetch the Cell object at the given location.
     * @param row row number (0-based)
     * @param column column number (0-based)
     * @return the Cell created for this position on the board
     * @throws OutOfBoundsException if either coordinate is negative or too high
     */
    // TODO getCell GOES HERE
    public Cell getCell(int row, int column){
        Cell cell = null;
        try {
            if (row >= 0 && row <= getHeight() && column >= 0 && column <= getWidth()) {
                cell = board[row][column];
            } else {
                throw new OutOfBoundsException(row, column);
            }
        }catch(OutOfBoundsException o){
            System.out.println(o);
        }
        return cell;
    }

    @Override //for testing
    public String toString(){
        return("height: " + height + " / width: " + width);
    }

    /**
     * displays the board in character form for the user
     * @param out the output stream to which the display should be sent
     */
    public void display(PrintStream out){
        out.print("\n ");
        for(int idx = 0; idx < width; idx++) {
            out.print(idx + " ");
        }
        out.println();

        for (int idx2 = 0; idx2 < height; idx2++) {
            out.print(idx2 + " ");
            for(int idx = 0; idx < width; idx++){
                out.print(board[idx2][idx].displayHitStatus() + " ");

            }
            out.println();
        }
    }

    /**
     * display that shows the user the unsunk parts of ships
     * @param out the ouput stream to which the display should be sent
     */
    public void fullDisplay(PrintStream out){
        out.print("\n ");
        for(int idx = 0; idx < width; idx++) {
            out.print(idx + " ");
        }
        out.println();

        for (int idx2 = 0; idx2 < height; idx2++) {
            out.print(idx2 + " ");
            for(int idx = 0; idx < width; idx++){
                out.print(board[idx2][idx].displayChar() + " ");

            }
            out.println();
        }
    }

    /**
     * Add a ship to the board. The only current reason that the
     * board needs direct access to the ships is to poll them
     * to see if they are all sunk and the game is over.
     * @see Cell#putShip(Ship)
     * @param ship the as-yet un-added ship
     * @rit.pre This ship has already informed the Cells of the board
     *    that are involved.
     */
    // TODO addShip GOES HERE
    public void addShip(Ship ship){
        ships.add(ship);
    }

    /**
     * true if all ships are sunk. Otherwise, false
     * @return boolean
     */
    public boolean allSunk(){
        boolean allSunk = false;

        for(int idx = 0; idx < ships.size(); idx++){
            if(!(ships.get(idx).isSunk())){
                allSunk = false;
            }
        }
        return allSunk = true;
    }
}
