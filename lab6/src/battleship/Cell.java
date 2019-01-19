/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 6, Cell.java
 */
package battleship;

import java.io.Serializable;

/**
 * A single spot on the Battleship game board.
 * A cell knows if there is a ship on it, and it remember
 * if it has been hit.
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';

    public Ship ship;
    private boolean hit;
    private int column;
    private int row;

    /**
     * created a new Cell
     * @param row int resebling a specific row
     * @param column int resembling a specific column
     */
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * Return a character representing the state of this Cell. This display method reveals all.
     * @return char
     */
    public char displayChar(){
        if(hit){
            if(ship != null){
                if(ship.isSunk()){
                    return SUNK_SHIP_SECTION;
                }else{
                    return HIT_SHIP_SECTION;
                }
            }else{
                return HIT_WATER;
            }
        }else {
            if (ship == null) {
                return PRISTINE_WATER;
            }else{
                return HIDDEN_SHIP_SECTION;
            }
        }
    }

    /**
     * Returns a character representing the state of this Cell but without revealing unhit portions of ships.
     * Unhit portions of ships appear as PRISTINE_WATER.
     * @return char
     */
    public char displayHitStatus(){
        if(hit){
            if(ship != null){
                if(ship.isSunk()){
                    return SUNK_SHIP_SECTION;
                }else{
                    return HIT_SHIP_SECTION;
                }
            }else{
                return HIT_WATER;
            }
        }else{
            return PRISTINE_WATER;
        }
    }

    /**
     * Simulates hitting this water cell.
     */
    public void hit() throws CellPlayedException{
        if(!hit){
            this.hit = true;
            if(ship != null){
                ship.hit();
            }
        }else{
            throw new CellPlayedException(row, column);
        }
    }

    /**
     * Place a ship on this cell. Of course, ships typically cover
     * more than one Cell, so the same ship will usually be passed
     * to more than one Cell's putShip method.
     * @param ship the ship that is to be on this Cell
     * @throws OverlapException if there is already a ship here.
     */
    // TODO putShip GOES HERE
    public void putShip(Ship ship) throws OverlapException{
        if(this.ship == null){
            this.ship = ship;
        }else{
            throw new OverlapException(row, column);
        }
    }
}
