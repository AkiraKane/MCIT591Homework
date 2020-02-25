package assignment_4;

import java.util.ArrayList;

/**
 * Self-designed templates. For all the templates within this class
 */
public class Template {
    /**
     * An object consists of a word and its stats. Mainly for sorting algorithm
     */
    public static class SuggestWord {
        private String word;
        private double stats;

        /**
         * Default constructor
         * @param givenWord the word itself
         * @param givenStats relevant stats
         */
        public SuggestWord(String givenWord, double givenStats) {
            this.word = givenWord;
            this.stats = givenStats;
        }

        /**
         * Getter
         * @return the word
         */
        public String getWord() {
            return word;
        }

        /**
         * Getter
         * @return the stats
         */
        public double getStats() {
            return stats;
        }
    }

    /**
     * An object consists of a word, its letters and its length. Mainly for building the dictionary
     */
    public static class DictWord {
        public String word;
        public ArrayList<Character> letters = new ArrayList<>();
        public int wordLength;

        /**
         * Deafult constructor, calculate the word length and add letters to the letter list
         * @param givenWord is the word itself
         */
        public DictWord(String givenWord) {
            this.word = givenWord;
            this.wordLength = givenWord.length();
            for (int i = 0; i < this.wordLength; i++) {
                if (!this.letters.contains(this.word.charAt(i))) {
                    this.letters.add(this.word.charAt(i));
                }
            }
        }

        /**
         * Getter
         * @return the word
         */
        public String getWord() {
            return word;
        }

        /**
         * Getter
         * @return the list of letters it has
         */
        public ArrayList<Character> getLetters() {
            return letters;
        }

        /**
         * Getter
         * @return the word length
         */
        public int getWordLength() {
            return wordLength;
        }
    }
}
