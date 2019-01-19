package student;

import model.Baron;
import model.Orientation;
import model.Space;
import model.Station;
import model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a train route in the Railroad Barons game. A route comprises tracks between two stations on the map.
 * Valid routes must include two distinct (non-equal) stations, must be either horizontal or vertical, and the origin
 * station must be north of or to the west of the destination station (this simplifies some of the route methods).
 *
 * @author Ryan Keihm
 */
public class Route implements model.Route {
    private Baron baron;
    private Station origin;
    private Station destination;
    private Orientation orientation;
    private List<Track> tracks = new ArrayList<>();

    /**
     * The constructor for Route
     * @param origin starting origin of the station
     * @param destination ending destination of the station
     * @param baronString the actual baron
     */
    public Route(Station origin, Station destination, String baronString) {
        Baron.valueOf(baronString);
        this.baron = Baron.UNCLAIMED;
        this.origin = origin;
        this.destination = destination;
    }

    /**
     * Returns the Baron that has claimed this route. Note that this route may be unclaimed.
     *
     * @return The Baron that has claimed this route.
     */
    @Override
    public Baron getBaron() {
        return this.baron;
    }

    /**
     * Returns the station at the beginning of this route. The origin must be directly north of or to the west of the
     * destination.
     *
     * @return The Station at the beginning of this route.
     */
    @Override
    public Station getOrigin() {
        return this.origin;
    }

    /**
     * Returns the station at the end of this route. The destination must be directly south of or to the east of
     * the origin.
     *
     * @return The Station at the end of this route.
     */
    @Override
    public Station getDestination() {
        return this.destination;
    }

    /**
     * Returns the orientation of this route; either horizontal or vertical.
     *
     * @return The Orientation of this route.
     */
    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    /**
     * The set of tracks that make up this route.
     *
     * @return The List of tracks that make up this route.
     */
    @Override
    public List<Track> getTracks() {
        return this.tracks;
    }

    /**
     * Returns the length of the route, not including the stations at the end points.
     *
     * @return The number of Tracks comprising this route.
     */
    @Override
    public int getLength() {
        return this.tracks.size();
    }

    /**
     * Returns the number of points that this route is worth according to the following algorithm:
     * 1 - 1 point
     * 2 - 2 points
     * 3 - 4 points
     * 4 - 7 points
     * 5 - 10 points
     * 6 - 15 points
     * 7 (or more) - 5 * (length - 3) points
     *
     * @return The number of points that this route is worth.
     */
    @Override
    public int getPointValue() {
        switch (this.tracks.size()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 10;
            case 6:
                return 15;
            default:
                if (this.tracks.size() >= 7) {
                    return 5 * (this.tracks.size() - 3);
                } else {
                    System.out.println("getPointValue Error");
                    return 0;
                }
        }
    }

    /**
     * Returns true if the route covers the ground at the location of the specified space and false otherwise.
     *
     * @param space The {@link Space} that may be in this route.
     * @return True if the Space's coordinates are a part of this route, and false otherwise.
     */
    @Override
    public boolean includesCoordinate(Space space) {
        for (Track track : tracks) {
            if (track.getRow() == space.getRow() && track.getCol() == space.getCol()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Attempts to claim the route on behalf of the specified Baron. Only unclaimed routes may be claimed.
     *
     * @param claimant The {@link Baron} attempting to claim the route. Must
     *                 not be null or {@link Baron#UNCLAIMED}.
     * @return True if the route was successfully claimed. False otherwise.
     */
    @Override
    public boolean claim(Baron claimant) {
        if (claimant.equals(Baron.UNCLAIMED) || claimant.equals(null)) {
            return false;
        } else {
            this.baron = claimant;
            return true;
        }
    }
}