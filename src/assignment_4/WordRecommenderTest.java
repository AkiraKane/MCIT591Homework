package assignment_4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WordRecommenderTest {

    @Test
    void getSimilarityMetric() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");
        double result1 = recommender.getSimilarityMetric("olaf", "oluf");
        assertEquals(result1, 3);
    }

    @Test
    void getWordSuggestions() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");
        ArrayList<String> result = recommender.getWordSuggestions("whut", 3, 0.5, 10);
        System.out.println(recommender.prettyPrint(result));
    }

    @Test
    void getWordsWithCommonLetters() {
    }

    @Test
    void prettyPrint() {
    }
}