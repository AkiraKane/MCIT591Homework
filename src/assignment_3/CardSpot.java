package assignment_3;

/**
 * A positioned spot that places a single card
 */
public class CardSpot {

    /**
     * The card that the spot places
     */
    private Card cardPlaced;

    /**
     * The position of the spot, range: [1, 20]
     */
    private int position;

    /**
     * Default constructor
     * @param index given position
     */
    public CardSpot(int index) {
        this.cardPlaced = new Card(); // Place a default void card
        this.position = index; // Set the position
    }

    /**
     * Prints the card placed
     */
    public void showCard() {
        if (this.cardPlaced.getType().equals("")) {
            if (this.position < 10) {
                System.out.print("  " + String.valueOf(this.position));
            }
            else {
                System.out.print(" " + String.valueOf(this.position));
            }
        }
        else {
            if (this.cardPlaced.getRank().equals("10")) {
                System.out.print(this.cardPlaced.getType());
            }
            else {
                System.out.print(" " + this.cardPlaced.getType());
            }
        }
    }

    /**
     * Place a new card in the spot
     * @param givenCard the card that is going to place
     */
    public void placeCard(Card givenCard) {
        this.cardPlaced = givenCard;
    }

    // All the getters

    public Card getCardPlaced() {
        return cardPlaced;
    }

    public int getPosition() {
        return position;
    }
}
