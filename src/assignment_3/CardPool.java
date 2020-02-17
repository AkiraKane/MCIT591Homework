package assignment_3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A pool of cards that the game will deal cards from
 */
public class CardPool {
    /**
     * A set of all 52 french playing cards
     */
    private ArrayList<Card> cards = new ArrayList<Card>();

    /**
     * Default constructor
     */
    public CardPool() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"S", "C", "H", "D"};
        for (String rank: ranks) {
            for (String suit: suits) {
                Card newCard = new Card(rank, suit);
                cards.add(newCard);
            }
        }
    }

    /**
     * Shuffle the CardPool
     */
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     * Remove the card at the top
     * @return the card at the top
     */
    public Card removeTopCard() {
        Card removedCard = this.cards.remove(0);
        return removedCard;
    }

    // The getter

    public ArrayList<Card> getCards() {
        return cards;
    }
}
