/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, Ship.java
 */
package battleship;

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {

    private Ship.Orientation ort;
    private int totalHits = 0;
    private Board board;
    private int length;
    private int uRow;
    private int lCol;

    public static final String SUNK_MESSAGE = "A battleship has been sunk!";

    /**
     * Orientation is a property of a ship.
     * The names of the enum values were chosen because they
     * are descriptive and match the words put in the configuration
     * files.
     *
     * @see Orientation#valueOf(String)
     */
    public enum Orientation {
        HORIZONTAL( 0, 1 ), VERTICAL( 1, 0 );

        /**
         * Use it to loop through all of the cell locations a ship
         * is on, based on the upper left end of the ship.
         */
        public int rDelta, cDelta;

        /**
         * Associate a direction vector with the orientation.
         * @param rDelta how much the row number changes in this direction
         * @param cDelta how much the column number changes
         *               in this direction
         */
        Orientation( int rDelta, int cDelta ) {
            this.rDelta = rDelta;
            this.cDelta = cDelta;
        }
    }

    /**
     * tells the ship sitting on a specific cell that it is hit
     */
    public void hit(){
        if(isSunk()){
            System.out.println(SUNK_MESSAGE);
        }else{
            totalHits++;
        }
    }

    /**
     * tells if the ship is sunk or not
     * @return boolean
     */
    public boolean isSunk(){
        if(totalHits >= length){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Initialize this new ship's state. Tell the Board object
     * and each involved Cell object about the existence of this
     * ship by trying to put the ship at each applicable Cell.
     * @param board holds a collection of ships
     * @param uRow the uppermost row that the ship is on
     * @param lCol the leftmost column that the ship is on
     * @param ort the ship's orientation
     * @param length how many cells the ship is on
     * @throws OverlapException if this ship would overlap another one
     *              that already exists
     * @throws OutOfBoundsException if this ship would extend beyond
     *              the board
     */
    // TODO Write your code here.
    public Ship(Board board, int uRow, int lCol, Ship.Orientation ort, int length) throws OutOfBoundsException{
        this.board = board;
        this.uRow = uRow;
        this.lCol = lCol;
        this.ort = ort;
        this.length = length;

        if(ort == Orientation.HORIZONTAL && lCol > board.getWidth()){
            throw new OutOfBoundsException(uRow, lCol);
        }
        if(ort == Orientation.VERTICAL && uRow + length > board.getHeight()){
            throw new OutOfBoundsException(uRow, lCol);
        }
    }
}
