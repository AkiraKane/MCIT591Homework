package assignment_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class WordRecommender {
    private String dictName;
    private ArrayList<Template.DictWord> dictionary = new ArrayList<>();

    /**
     * Default constructor that adds each word from the txt to the dictionary
     * @param fileName is the filename of the dictionary
     */
    public WordRecommender(String fileName) {
        this.dictName = fileName;
        File inputFile = new File(this.dictName);
        try {
            Scanner inScan = new Scanner(inputFile);
            while (inScan.hasNext()) {
                this.dictionary.add(new Template.DictWord(inScan.next()));
            }
            inScan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter
     * @return the dictionary
     */
    public ArrayList<Template.DictWord> getDictionary() {
        return this.dictionary;
    }

    /**
     * Computes two measures of similarity
     * @param word1 is the first given word
     * @param word2 is the second given word
     * @return the average metric
     */
    public double getSimilarityMetric(String word1, String word2) {
        int leftSimilarity = 0, rightSimilarity = 0, i = 0, j = 0, length1 = word1.length(), length2 = word2.length();
        while ((i < length1) && (j < length2)) {
            if (word1.charAt(i) == word2.charAt(j)) {
                leftSimilarity++;
            }
            i++;
            j++;
        } // LeftSimilarity
        i = length1 - 1;
        j = length2 - 1;
        while ((i >= 0) && (j >= 0)) {
            if (word1.charAt(i) == word2.charAt(j)) {
                rightSimilarity++;
            }
            i--;
            j--;
        } // RightSimilarity
        return (double) (rightSimilarity + leftSimilarity) / 2; // Average Similarity
    }

    /**
     * Give suggestions of feasible words that can replace the incorrect word
     * @param word is the incorrect word
     * @param n is length difference
     * @param commonPercent is the designated similarity
     * @param topN is the designated number of most similar words
     * @return top n suggestions
     */
    public ArrayList<String> getWordSuggestions(String word, int n, double commonPercent, int topN) {
        int wordLength = word.length();
        ArrayList<Character> wordLetters = new ArrayList<>();

        for (int index = 0; index < wordLength; index++) {
            if (!wordLetters.contains(word.charAt(index))) {
                wordLetters.add(word.charAt(index));
            }
        }

        ArrayList<Template.SuggestWord> suggestedWords = new ArrayList<>();
        for (Template.DictWord dictWord: this.dictionary) {
            int unionNum = wordLetters.size(), intersectNum = 0;

            for (char dictWordLetter: dictWord.letters) {
                if (wordLetters.contains(dictWordLetter)) {
                    intersectNum++;
                }
                else {
                    unionNum++;
                }
            }

            double thisCommonPercent = (double) intersectNum / unionNum;

            boolean isCriteriaMet1 = (thisCommonPercent >= commonPercent);
            boolean isCriteriaMet2 = ((dictWord.wordLength >= wordLength - n) && (dictWord.wordLength <= wordLength + n));

            if (isCriteriaMet1 && isCriteriaMet2) {
                suggestedWords.add(new Template.SuggestWord(dictWord.word, this.getSimilarityMetric(word, dictWord.word)));
            }
        }

        int low = 0, high = suggestedWords.size() - 1;
        SortAlgorithm.mergeSort(suggestedWords, low, high);

        ArrayList<String> suggestions = new ArrayList<>();
        int i = 0, j = 0;
        while ((i < topN) && (j < suggestedWords.size())) {
            suggestions.add(suggestedWords.get(j).word);
            i++;
            j++;
        }
        return suggestions;
    }

    /**
     * Provide list of words in the dictionary that have at least (>=) n letters in common
     * @param word is the given word
     * @param listOfWords is the given list of words from a dictionary
     * @param n is the number of letters that are at least in common
     * @return the list of words in the dictionary that have at least (>=) n letters in common
     */
    public ArrayList<String> getWordsWithCommonLetters(String word, ArrayList<String> listOfWords, int n) {
        int wordLength = word.length();
        ArrayList<Character> wordLetters = new ArrayList<>();

        for (int index = 0; index < wordLength; index++) {
            if (!wordLetters.contains(word.charAt(index))) {
                wordLetters.add(word.charAt(index));
            }
        }

        ArrayList<String> feasibleWords = new ArrayList<>();
        for (String possibleWord: listOfWords) {
            int possibleWordLength = possibleWord.length();
            ArrayList<Character> possibleWordLetters = new ArrayList<>();

            for (int index = 0; index < possibleWordLength; index++) {
                if (!possibleWordLetters.contains(possibleWord.charAt(index))) {
                    possibleWordLetters.add(possibleWord.charAt(index));
                }
            }

            int commonLetterNum = 0;
            for (char letter: possibleWordLetters) {
                if (wordLetters.contains(letter)) {
                    commonLetterNum++;
                }
            }

            if (commonLetterNum >= n) {
                feasibleWords.add(possibleWord);
            }
        }
        return feasibleWords;
    }

    /**
     * Purely for display purposes
     * @param list is a list of words
     * @return a String which when printed will have the list elements with a number in front of them
     */
    public String prettyPrint(ArrayList<String> list) {
        StringBuilder concatenation = new StringBuilder();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            concatenation.append(i);
            concatenation.append(". ");
            concatenation.append(list.get(i));
            concatenation.append("\n");
        }
        return concatenation.toString();
    }

}
