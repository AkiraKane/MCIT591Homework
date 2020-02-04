package assignment_3;

import java.util.Scanner;

/**
 * A BlackjackSolitaire game
 */
public class BlackjackSolitaire {
    /**
     * The deck of the game
     */
    private Deck deck = new Deck();

    /**
     * The cardPool that cards are from
     */
    private CardPool cardPool = new CardPool();

    /**
     * Check if the player's move is invalid
     * @param move the spot that the player designates
     * @return true if the move is invalid
     */
    public boolean isFalseMove(int move) {
        if ((move < 1) || (move > 20)) { // Check whether the designated position is not in range 1-20
            System.out.println("Out of range! Please reselect!");
            return true;
        }
        else if (!(this.deck.getSpots()[move - 1].getCardPlaced().getType().equals(""))) { // Check if there is a card
            System.out.println("Position is already taken! Please reselect!");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Deals a card from the cardPool and let the player to put the card to a specific spot
     */
    public void dealCard() {
        Card currentCard = this.cardPool.removeTopCard(); // Get the top card and move it from the cardPool
        System.out.println("Card to put -> " + currentCard.getType());
        System.out.print("Where to put (1-20)?: ");
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        while (this.isFalseMove(index)) { // Check if the move is valid
            System.out.println("Card to put -> " + currentCard.getType());
            System.out.print("Where to put (1-20)?: ");
            index = scanner.nextInt();
        }
        this.deck.getSpots()[index - 1].placeCard(currentCard); // Place the card
    }

    /**
     * Play the game
     */
    public void play()
    {
        System.out.println("Game Starts!");
        this.deck.showDeck(); // Show the empty deck
        cardPool.shuffle(); // Shuffle the cardPool
        while (this.deck.isNotFull()) { // Play the game
            this.dealCard();
            this.deck.showDeck();
        }
        System.out.println("Deck is full!");
        this.deck.getScore(); // Calculate the score
        System.out.println("Game Ends!");
    }
}
