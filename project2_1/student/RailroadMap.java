/**
 * Language: Java
 * Author(s): Jason Hicks
 * Project 2 (Part 1), RailroadMap.java
 */
package student;

import model.*;
import model.RailroadMapObserver;
import model.Route;
import model.Space;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RailroadMap implements model.RailroadMap {

    private Set<RailroadMapObserver> mapObservers = new HashSet<>();
    private Set<Space> spaces;
    public List<Route> routes;

    public RailroadMap(Set spaces, List routes){
        this.spaces = spaces;
        this.routes = routes;
    }

    /**
     * Adds the specified observer to the map.
     * @param observer The {@link RailroadMapObserver} being added to the map.
     */
    @Override
    public void addObserver(RailroadMapObserver observer) {
        mapObservers.add(observer);

    }

    /**
     * Removes the specified observer from the map.
     * @param observer The observer to remove from the collection of
     *                 registered observers that will be notified of
     */
    @Override
    public void removeObserver(RailroadMapObserver observer) {
        mapObservers.remove(observer);
    }

    /**
     * Returns the number of rows in the map.
     * @return rows
     */
    @Override
    public int getRows() {
        int currentSpace = 0;

        for(Space space: spaces){
            if(space.getRow() > currentSpace){
                currentSpace = space.getRow();
            }
        }
        return currentSpace + 1;
    }

    /**
     * Returns the number of columns in the map.
     * @return column (int)
     */
    @Override
    public int getCols() {
        int  currentSpace = 0;

        for(Space space: spaces){
            if(space.getCol() > currentSpace){
                currentSpace = space.getCol();
            }
        }
        return currentSpace + 1;
    }

    /**
     *
     * @param row The row of the desired {@link Space}.
     * @param col The column of the desired {@link Space}.
     *
     * @return Space (Object)
     */
    @Override
    public Space getSpace(int row, int col) {

        for(Space space: spaces){
            if(space.getCol() == col && space.getRow() == row){
                return  space;
            }
        }
        return null;
    }

    /**
     * Returns the route that contains the track at the specified location (if such a route exists}.
     * @param row The row of the location of one of the {@link Track tracks}
     *            in the route.
     * @param col The column of the location of one of the
     * {@link Track tracks} in the route.
     *
     * @return Route(Object)
     */
    @Override
    public Route getRoute(int row, int col) {
        Space space = new student.Space(row, col);
        if(spaces.contains(space)){
            space = getSpace(row, col);
            if(space instanceof Track){
                return ((Track) space).getRoute();
            }
        }
        return null;
    }

    /**
     * Called to update the map when a Baron has claimed a route.
     * @param route The {@link Route} that has been claimed.
     */
    @Override
    public void routeClaimed(Route route) {

        for(RailroadMapObserver observer : mapObservers){
            observer.routeClaimed(this, route);
        }
    }

    /**
     * Returns the length of the shortest unclaimed route in the map.
     * @return int
     */
    @Override
    public int getLengthOfShortestUnclaimedRoute() {
        int result = routes.indexOf(0);

        for(Route route : routes){
            if(route.getLength() < result && route.getBaron().equals(Baron.UNCLAIMED)){
                result = route.getLength();
            }
        }
        return result;
    }

    /**
     * Returns all of the Routes in this map.
     * @return Collection<Route (object) >
     */
    @Override
    public Collection<Route> getRoutes() {
        return this.routes;
    }
}
