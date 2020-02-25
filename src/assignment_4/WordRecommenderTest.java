package assignment_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class WordRecommenderTest {

    @org.junit.jupiter.api.Test
    void getSimilarityMetric() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");
        recommender.getSimilarityMetric("olaf", "olaf");
    }

    @org.junit.jupiter.api.Test
    void getWordSuggestions() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");
        String result = recommender.prettyPrint(recommender.getWordSuggestions("disslve", 3, 0.8, 11));
        System.out.println(result);
    }

    @org.junit.jupiter.api.Test
    void getWordsWithCommonLetters() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");
        ArrayList<String> testcase = new ArrayList<>();
        File userFile = new File("test.txt");
        try {
            Scanner inScan = new Scanner(userFile);
            while (inScan.hasNext()) {
                testcase.add(inScan.next());
            }
            inScan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> result = recommender.getWordsWithCommonLetters("cardiovascular", testcase, 4);
        System.out.println(recommender.prettyPrint(result));
    }
}