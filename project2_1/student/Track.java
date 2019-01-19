/**
 * Language: Java
 * Author(s): Jason Hicks
 * Project 2 (Part 1), Track.java
 */
package student;

import model.Baron;
import model.Orientation;
import model.Route;
import model.Space;

import java.sql.SQLOutput;
import java.util.Objects;

/**
 * Represents a track segment on the map. Tracks combine to form routes.
 */
public class Track extends student.Space implements model.Track {

    private Orientation orient;
    private int row;
    private int column;
    private Baron baron;
    private Route route;

    /**
     * Creates a Track object.
     * @param row this.(int)
     * @param column this.(int)
     * @param route Route (Object)
     */
    public Track(int row, int column, Route route){
        super(row, column);
        this.baron = route.getBaron();
        this.row = row;
        this.column = column;
        this.orient = route.getOrientation();
        this.route = route;
    }

    /**
     * Returns the orientation of the track; either horizontal or vertical.
     * @return orientation
     */
    @Override
    public Orientation getOrientation() {
        if(Objects.equals(this.orient, Orientation.VERTICAL)){
            return Orientation.VERTICAL;
        }else if(Objects.equals(this.orient, Orientation.HORIZONTAL))  {
            return Orientation.HORIZONTAL;
        }else{
            System.out.println("getOrientation Error.");
            return null;
        }
    }

    /**
     * Returns the current owner of this track, either unclaimed if the track has not been claimed, or the owner that
     * corresponds with the color of the player that successfully claimed the route of which this track is a part.
     * @return owner of current track (Object)
     */
    @Override
    public Baron getBaron() {
        return this.baron;
    }

    /**
     * Returns the route of which this track is a part.
     * @return route (Object)
     */
    @Override
    public Route getRoute() {
        return this.route;
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