package assignment_3;

import java.util.ArrayList;

/**
 * A deck that has 16 spots to put cards on and 4 spots to discard cards
 */
public class Deck {
    /**
     * All the spots
     */
    private CardSpot[] spots;

    /**
     * Default constructor: set positioned spots 1-20 with void cards
     */
    public Deck() {
        this.spots = new CardSpot[20];
        for (int i = 0; i < 20; i++) {
            CardSpot eachDefaultSpot = new CardSpot(i + 1);
            this.spots[i] = eachDefaultSpot;
        }
    }

    /**
     * Calculate the total score of one hand
     * @param containerIndices given the position of spots in the hand
     * @return the score of one hand
     */
    public int oneHandScore(int[] containerIndices) {
        int countA = 0;
        int values = 0;
        int handSize = containerIndices.length; // count the number of spots in the hand

        for (int index: containerIndices) {
            if (this.spots[index].getCardPlaced().getRank().equals("A")) {
                countA += 1; // count the number of card A
            }
            values += this.spots[index].getCardPlaced().getValue(); // count initial total value
        }

        if ((values < 12) && (countA != 0)) { // determine A's value to be 11 or not and update the total value
            values += 10;
        }

        switch(values) { // return total score of the hand
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return 1;
            case 17:
                return 2;
            case 18:
                return 3;
            case 19:
                return 4;
            case 20:
                return 5;
            case 21:
                if (handSize == 2) {
                    return 10;
                }
                else {
                    return 7;
                }
            default:
                return 0;
        }
    }

    /**
     * Prints the total score of all nine hands
     */
    public void getScore() {
        int[] rowOne = {0, 1, 2, 3, 4};
        int[] rowTwo = {5, 6, 7, 8, 9};
        int[] rowThree = {10, 11, 12};
        int[] rowFour = {13, 14, 15};
        int[] columnOne = {0, 5};
        int[] columnTwo = {1, 6, 10, 13};
        int[] columnThree = {2, 7, 11, 14};
        int[] columnFour = {3, 8, 12, 15};
        int[] columnFive = {4, 9};

        ArrayList<int[]> indicesList = new ArrayList<>();
        indicesList.add(rowOne);
        indicesList.add(rowTwo);
        indicesList.add(rowThree);
        indicesList.add(rowFour);
        indicesList.add(columnOne);
        indicesList.add(columnTwo);
        indicesList.add(columnThree);
        indicesList.add(columnFour);
        indicesList.add(columnFive);

        int totalScore = 0;
        for (int[] indexRange: indicesList) {
            int eachScore = this.oneHandScore(indexRange);
//            System.out.print(eachScore);
            totalScore += eachScore;
        }
        System.out.println("\nFINAL SCORE -> " + totalScore + " <-"); // Print the total score
    }

    /**
     * Prints the current state of the deck
     */
    public void showDeck() {
        System.out.println("|--------Current Deck--------|");
        for (int i = 0; i < 5; i++) {
            this.spots[i].showCard();
            System.out.print("   ");
        }
        System.out.println();
        for (int i = 5; i < 10; i++) {
            this.spots[i].showCard();
            System.out.print("   ");
        }
        System.out.println();
        System.out.print("      ");
        for (int i = 10; i < 13; i++) {
            this.spots[i].showCard();
            System.out.print("   ");
        }
        System.out.println();
        System.out.print("      ");
        for (int i = 13; i < 16; i++) {
            this.spots[i].showCard();
            System.out.print("   ");
        }
        System.out.println("\n|------Current Discards------|");
        for (int i = 16; i < 20; i++) {
            this.spots[i].showCard();
            System.out.print("   ");
        }
        System.out.println("\n|----------------------------|");
    }

    /**
     * Check if all the 16 spots are placed with cards
     * @return true if there are empty spots
     */
    public boolean isNotFull() {
        int filledNumber = 0;
        for (int i = 0; i < 16; i++) {
            if (!(this.spots[i].getCardPlaced().getType().equals(""))) {
                filledNumber += 1;
            }
        }
        return filledNumber < 16;
    }

    // The getter

    public CardSpot[] getSpots() {
        return spots;
    }
}