package assignment_3;

/**
 * Card with rank, suit and corresponding value for the game
 */
public class Card {
    /**
     * Type = rank + suit
     */
    private String type;
    private String rank;
    private String suit;

    /**
     * Corresponding value for the game
     */
    private int value;

    /**
     * Default constructor: a void card
     */
    public Card()
    {
        this.rank = "";
        this.suit = "";
        this.type = this.rank + this.suit;
        this.value = 0;
    }

    /**
     * Constructor
     * @param givenRank given rank of the card
     * @param givenSuit given suit of the card
     */
    public Card(String givenRank, String givenSuit) {
        this.rank = givenRank;
        this.suit = givenSuit;
        this.type = this.rank + this.suit;

        // Determine value with regard to the rank
        switch(givenRank) {
            case "2":
                this.value = 2;
                break;
            case "3":
                this.value = 3;
                break;
            case "4":
                this.value = 4;
                break;
            case "5":
                this.value = 5;
                break;
            case "6":
                this.value = 6;
                break;
            case "7":
                this.value = 7;
                break;
            case "8":
                this.value = 8;
                break;
            case "9":
                this.value = 9;
                break;
            case "10":
            case "Q":
            case "J":
            case "K":
                this.value = 10;
                break;
            case "A":
                this.value = 1; // initialize the value of card A to be 1
                break;
        }
    }

    // All the getters

    public String getType() {
        return type;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }
}
