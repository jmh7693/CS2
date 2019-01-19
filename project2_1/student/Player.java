package student;

import model.*;
import model.Pair;
import model.PlayerObserver;
import model.Route;

import java.util.Collection;

/**
 * The class that represents a player in a Railroad Barons game.
 *
 * @author Ryan Keihm and Jason Hicks
 */
public class Player implements model.Player {
    public student.Route route;
    public student.Pair pair;
    public Card card;

    /**
     * This is called at the start of every game to reset the player to its initial state:
     * Number of train pieces reset to the starting number of 45.
     * All remaining cards cleared from hand.
     * Score reset to 0.
     * Claimed routes cleared.
     * Sets the most recently dealt Pair of cards to two Card.NONE values.
     *
     * @param dealt The hand of {@link Card cards} dealt to the player at the
     */
    @Override
    public void reset(Card... dealt) {

    }

    /**
     * Adds an observer that will be notified when the player changes in some way.
     *
     * @param observer The new {@link PlayerObserver}.
     */
    @Override
    public void addPlayerObserver(PlayerObserver observer) {
    }

    /**
     * Removes an observer so that it is no longer notified when the player changes in some way.
     *
     * @param observer The {@link PlayerObserver} to remove.
     */
    @Override
    public void removePlayerObserver(PlayerObserver observer) {

    }

    /**
     * The baron as which this player is playing the game.
     *
     * @return The Baron as which this player is playing.
     */
    @Override
    public Baron getBaron() {
        return null;
    }

    /**
     * Used to start the player's next turn. A pair of cards is dealt to the player, and the player is once again able
     * to claim a route on the map.
     *
     * @param dealt a {@linkplain Pair pair of cards} to the player. Note that
     */
    @Override
    public void startTurn(Pair dealt) {

    }

    /**
     * Returns the most recently dealt pair of cards. Note that one or both of the cards may have a value of Card.NONE.
     *
     * @return The most recently dealt Pair of Cards.
     */
    @Override
    public Pair getLastTwoCards() {
        return null;
    }

    /**
     * Returns the number of the specific kind of card that the player currently has in hand. Note that the number
     * may be 0.
     *
     * @param card The {@link Card} of interest.
     * @return
     */
    @Override
    public int countCardsInHand(Card card) {
        return 0;
    }

    /**
     * Returns the number of game pieces that the player has remaining. Note that the number may be 0.
     *
     * @return The number of game pieces that the player has remaining.
     */
    @Override
    public int getNumberOfPieces() {
        return 0;
    }

    /**
     * Returns true iff the following conditions are true:
     * The route is not already claimed by this or some other baron.
     * The player has not already claimed a route this turn (players are limited to one claim per turn).
     * The player has enough cards (including ONE wild card, if necessary) to claim the route.
     * The player has enough train pieces to claim the route.
     *
     * @param route The {@link Route} being tested to determine whether or not
     *              the player is able to claim it.
     * @return True if the player is able to claim the specified Route, and false otherwise.
     */
    @Override
    public boolean canClaimRoute(Route route) {
        return false;
    }

    /**
     * Claims the given route on behalf of this player's Railroad Baron. It is possible that the player has enough
     * cards in hand to claim the route by using different combinations of card. It is up to the implementor to employ
     * an algorithm that determines which cards to use, but here are some suggestions:
     * Use the color with the lowest number of cards necessary to match the length of the route.
     * Do not use a wild card unless absolutely necessary (i.e. the player has length-1 cards of some color in hand
     * and it is the most numerous card that the player holds).
     *
     * @param route The {@link Route} to claim.
     * @throws RailroadBaronsException
     */
    @Override
    public void claimRoute(Route route) throws RailroadBaronsException {

    }

    /**
     * Returns the collection of routes claimed by this player.
     *
     * @return The Collection of Routes claimed by this player.
     */
    @Override
    public Collection<Route> getClaimedRoutes() {
        return null;
    }

    /**
     * Returns the players current score based on the point value of each route that the player has currently claimed.
     *
     * @return The player's current score.
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * Returns true iff the following conditions are true:
     * The player has enough cards (including wild cards) to claim a route of the specified length.
     * The player has enough train pieces to claim a route of the specified length.
     *
     * @param shortestUnclaimedRoute The length of the shortest unclaimed
     *                               {@link Route} in the current game.
     * @return True if the player can claim such a route, and false otherwise.
     */
    @Override
    public boolean canContinuePlaying(int shortestUnclaimedRoute) {
        return false;
    }
}
