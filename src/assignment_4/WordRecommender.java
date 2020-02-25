package assignment_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordRecommender {
    private String dictName;
    private ArrayList<Template.DictWord> dictionary = new ArrayList<>();

    /**
     * Default constructor that adds each word from the txt to the dictionary
     * @param fileName the filename of the dictionary
     */
    public WordRecommender(String fileName) {
        this.dictName = fileName;
        File inputFile = new File(this.dictName);
        try {
            Scanner in = new Scanner(inputFile);
            while (in.hasNextLine()) {
                dictionary.add(new Template.DictWord(in.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Computes two measures of similarity
     * @param word1 the first given word
     * @param word2 the second given word
     * @return the average
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
     * @param word the incorrect word
     * @param n length difference
     * @param commonPercent similarity
     * @param topN the number of most similar words
     * @return Top n suggestions
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
                suggestedWords.add(new Template.SuggestWord(dictWord.word, thisCommonPercent));
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

//    public ArrayList<String> getWordsWithCommonLetters(String word, ArrayList<String> listOfWords, int n) {
//
//    }
//
//    public String prettyPrint(ArrayList<String> list) {
//
//    }

    public static void main(String[] args) {
        WordRecommender recommend = new WordRecommender("dictionary.txt");
        double result = recommend.getSimilarityMetric("oblige", "oblivion");
        ArrayList<String> results = recommend.getWordSuggestions("abundonat", 10, 0.70, 5);

        System.out.println("END");
    }
}
