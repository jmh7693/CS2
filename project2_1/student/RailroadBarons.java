/**
 * Language: Java
 * Author(s): definitely Ryan Keihm
 * Project 2 (Part 1), RailroadBarons.java
 */
package student;

import model.Deck;
import model.Player;
import model.RailroadBaronsException;
import model.RailroadBaronsObserver;
import model.RailroadMap;

import java.util.Collection;

public class RailroadBarons implements model.RailroadBarons {

    /**
     * Adds a new observer to the collection of observers that will be notified when the state of the game changes.
     * @param observer The {@link RailroadBaronsObserver} to add to the
     */
    @Override
    public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {

    }

    /**
     * Removes the observer from the collection of observers that will be notified when the state of the game changes.
     * @param observer The {@link RailroadBaronsObserver} to remove.
     */
    @Override
    public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {

    }

    /**
     * Starts a new Railroad Barons game with the specified map and a default deck of cards.
     * @param map The {@link RailroadMap} on which the game will be played.
     */
    @Override
    public void startAGameWith(RailroadMap map) {

    }

    /**
     * Starts a new Railroad Barons game with the specified map and deck of cards.
     * @param map The {@link RailroadMap} on which the game will be played.
     * @param deck The {@link Deck} of cards used to play the game. This may
     *             be ANY implementation of the {@link Deck} interface,
     *             meaning that a valid implementation of the
     *             {@link RailroadBarons} interface should use only the
     */
    @Override
    public void startAGameWith(RailroadMap map, Deck deck) {

    }

    /**
     * Returns the map currently being used for play.
     * @return RailroadMap (Object)
     */
    @Override
    public RailroadMap getRailroadMap() {
        return null;
    }

    /**
     * Returns the number of cards that remain to be dealt in the current game's deck.
     * @return int
     */
    @Override
    public int numberOfCardsRemaining() {
        return 0;
    }

    /**
     * Returns true iff the current player can claim the route at the specified location, i.e.
     * @param row The row of a {@link Track} in the {@link Route} to check.
     * @param col The column of a {@link Track} in the {@link Route} to check.
     * @return boolean
     */
    @Override
    public boolean canCurrentPlayerClaimRoute(int row, int col) {
        return false;
    }

    /**
     * Attempts to claim the route at the specified location on behalf of the current player.
     * @param row The row of a {@link Track} in the {@link Route} to claim.
     * @param col The column of a {@link Track} in the {@link Route} to claim.
     * @throws RailroadBaronsException
     */
    @Override
    public void claimRoute(int row, int col) throws RailroadBaronsException {

    }

    /**
     * Called when the current player ends their turn.
     */
    @Override
    public void endTurn() {

    }

    /**
     * Returns the player whose turn it is.
     * @return Player (Object)
     */
    @Override
    public Player getCurrentPlayer() {
        return null;
    }

    /**
     * Returns all of the players currently playing the game.
     * @return Collection of Player objects
     */
    @Override
    public Collection<Player> getPlayers() {
        return null;
    }

    /**
     * Indicates whether or not the game is over.
     * @return boolean
     */
    @Override
    public boolean gameIsOver() {
        return false;
    }
}
