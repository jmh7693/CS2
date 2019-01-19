/**
 * Language: Java
 * Author(s): Ryan Keihm & Jason Hicks
 * Project 2 (Part 1), Station.java
 */
package student;

import model.Space;

import java.util.Objects;

/**
 * Represents a train station on the map. A train station is a space that has a name and is at one end (origin) or the
 * other (destination) of at least one train route.
 */
public class Station implements model.Station {
    private int row;
    private int column;
    private String name;

    /**
     * Creates a Station object.
     * @param row this.(int)
     * @param column this.(int)
     * @param name "Trainsville Station" (String)
     */
    public Station(int row, int column, String name){
        this.row = row;
        this.column = column;
        this.name = name;
    }

    /**
     * Returns the name of the station.
     * @return "Trainsville Station" (String)
     */
    @Override
    public String getName() {
        return this.name;
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

    /**
     * Returns true if the other space is occupying the same physical location in the map as this space.
     * @param other The other space to which this space is being compared for collocation.
     * @return (boolean)
     */
    @Override
    public boolean collocated(Space other) {
        if(Objects.equals(other.getRow(), this.getRow()) && Objects.equals(other.getCol(), this.getCol())){
            return true;
        }else return false;
    }
}