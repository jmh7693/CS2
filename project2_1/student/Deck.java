/**
 * Language: Java
 * Author(s): Ryan Keihm
 * Project 2 (Part 1), Deck.java
 */
package student;

import model.Card;

import java.util.Stack;

/**
 * Deck of cards used in a Railroad Barons game. The default deck has 20 of each type of playable card.
 */
public class Deck implements model.Deck {
    public Stack<Card> cards = new Stack<>();
    public Stack<Card> cardsOriginalState = new Stack<>();

    /**
     * Creates a deck of Card objects held in a Stack
     * @param cards Stack of Card objects
     */
    public Deck(Stack<Card> cards) {
        this.cards = cards;
        this.cardsOriginalState = cards;
    }

    /**
     * Resets the deck to its starting state.
     */
    @Override
    public void reset() {
        this.cards = this.cardsOriginalState;
    }

    /**
     * Draws the next card from the "top" of the deck.
     * @return Card (Object)
     */
    @Override
    public Card drawACard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        } else {
            return Card.NONE;
        }
    }

    /**
     * Returns the number of cards that have yet to be drawn.
     * @return int
     */
    @Override
    public int numberOfCardsRemaining() {
        return cards.size();
    }
}
