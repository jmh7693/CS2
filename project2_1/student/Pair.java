/**
 * Language: Java
 * Author(s): Ryan Keihm
 * Project 2 (Part 1), Pair.java
 */
package student;

import model.Card;

/**
 * Cards in a Railroad Barons game are dealt to each Player in pairs. This class is used to hold one such pair of
 * cards. Note that, if the deck is empty, one or both cards may have a value of "none."
 */
public class Pair implements model.Pair {
    public Card cardOne;
    public Card cardTwo;

    /**
     * Creates a pair of Card objects
     * @param cardOne first Card (Object)
     * @param cardTwo second Card (Object)
     */
    public Pair(Card cardOne, Card cardTwo){
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }

    /**
     * Returns the first card in the pair.
     * @return Card (Object)
     */
    @Override
    public Card getFirstCard() {
        return this.cardOne;
    }

    /**
     * Returns the second card in the pair.
     * @return Card (Object)
     */
    @Override
    public Card getSecondCard() {
        return this.cardTwo;
    }
}
