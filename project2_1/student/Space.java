/**
 * Language: Java
 * Author(s): Ryan Keihm & Jason Hicks
 * Project 2 (Part 1), Space.java
 */
package student;

import java.util.Objects;

/**
 * Represents a space on the Railroad Barons map.
 */
public class Space implements model.Space {
    private int row;
    private int column;

    /**
     * Creates a Space object.
     * @param row this.(int)
     * @param column this.(int)
     */
    public Space(int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the row of the space's location in the map.
     * @return row (int)
     */
    @Override
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of the space's location in the map.
     * @return column (int)
     */
    @Override
    public int getCol() {
        return this.column;
    }

    @Override
    public boolean collocated(model.Space other) {
        if(Objects.equals(other.getRow(), this.getRow()) && Objects.equals(other.getCol(), this.getCol())){
            return true;
        }else return false;
    }
}