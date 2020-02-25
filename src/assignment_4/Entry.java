package assignment_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Entry class for the spell-check program
 */
public class Entry {
    private static ArrayList<String> fileWords = new ArrayList<>();
    private static ArrayList<String> newWords = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to provide the user file
     * @return the name of the user file
     */
    public static String handleFileName () {
        System.out.print("Please provide the name of your file -> ");
        return Entry.scanner.nextLine();
    }

    /**
     * Create the name of the output file
     * @param givenFileName is the name of the user file provided by the user
     * @return the name of the output file
     */
    public static String handleOutFileName(String givenFileName) {
        StringBuilder outFileNameBuilder = new StringBuilder();
        int i = 0;
        while (givenFileName.charAt(i) != '.') {
            outFileNameBuilder.append(givenFileName.charAt(i));
            i++;
        }
        outFileNameBuilder.append("_chk");
        while (i < givenFileName.length()) {
            outFileNameBuilder.append(givenFileName.charAt(i));
            i++;
        }
        return outFileNameBuilder.toString();
    }

    /**
     * Store every word in the user file to the static instance "fileWords"
     * @param givenFileName is the name of the user file provided by the user
     */
    public static void storeFileWords(String givenFileName) {
        File userFile = new File(givenFileName);
        try {
            Scanner inScan = new Scanner(userFile);
            while (inScan.hasNext()) {
                Entry.fileWords.add(inScan.next());
            }
            inScan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main program that checks the spelling of every word from the user file
     * @param givenRecommender is the word recommendation system based on the dictionary
     */
    public static void spellCheck(WordRecommender givenRecommender) {
        for (String eachWord: Entry.fileWords) {
            boolean containWord = false;
            if (eachWord.equals("a")) {
                containWord = true;
            }
            else if (eachWord.equals("i")) {
                containWord = true;
            }
            else {
                for (int i = 0; i < givenRecommender.getDictionary().size(); i++) {
                    if (givenRecommender.getDictionary().get(i).getWord().equals(eachWord)) {
                        containWord = true;
                        break;
                    }
                }
            }

            if (!containWord) {
                ArrayList<String> suggestions = givenRecommender.getWordSuggestions(eachWord, 3, 0.6, 5);
                System.out.println("The word \"" + eachWord + "\" was probably misspelled.");
                if (suggestions.size() == 0) {
                    System.out.println("There are 0 suggestions in our dictionary for this word.");
                    System.out.print("Press 'a' for accept as is, 't' for type in manually -> ");

                    String choice = Entry.scanner.nextLine();
                    while (choice.equals("")) {
                        choice = Entry.scanner.nextLine();
                    }

                    while ((!choice.equals("a")) && (!choice.equals("t"))) {
                        System.out.print("Invalid input, please retry 'a' or 't' -> ");
                        choice = Entry.scanner.nextLine();
                    }
                    if (choice.equals("a")) {
                        System.out.println("The word \"" + eachWord + "\" " + "will remain unchanged.\n");
                        Entry.newWords.add(eachWord);
                    }
                    else {
                        System.out.print("Please type the word that will be used as the replacement in the output file -> ");
                        String replaceWord = Entry.scanner.nextLine();
                        System.out.println("The word \"" + eachWord + "\" " + "will be changed to \"" + replaceWord + "\".\n");
                        Entry.newWords.add(replaceWord);
                    }
                }
                else {
                    System.out.println("The following suggestions are available:");
                    System.out.println(givenRecommender.prettyPrint(suggestions));

                    System.out.print("Press 'r' for replace, 'a' for accept as is, 't' for type in manually -> ");
                    String choice = Entry.scanner.nextLine();
                    while (choice.equals("")) {
                        choice = Entry.scanner.nextLine();
                    }

                    while ((!choice.equals("r")) && (!choice.equals("a")) && (!choice.equals("t"))) {
                        System.out.print("Invalid input, please retry 'r', 'a' or 't' -> ");
                        choice = Entry.scanner.nextLine();
                    }
                    if (choice.equals("r")) {
                        System.out.println("Your word will now be replaced with one of the suggestions.");
                        System.out.print("Enter the number corresponding to the word that you want to use for replacement -> ");
                        int wordNumber = Entry.scanner.nextInt() - 1;
                        while ((wordNumber > 4) || (wordNumber < 0)) {
                            System.out.print("Invalid input, please re-enter the correct number -> ");
                            wordNumber = Entry.scanner.nextInt() - 1;
                        }
                        System.out.println("The word \"" + eachWord + "\" " + "will be changed to \"" + suggestions.get(wordNumber) + "\".\n");
                        Entry.newWords.add(suggestions.get(wordNumber));
                    }
                    else if (choice.equals("a")) {
                        System.out.println("The word \"" + eachWord + "\" " + "will remain unchanged.\n");
                        Entry.newWords.add(eachWord);
                    }
                    else {
                        System.out.print("Please type the word that will be used as the replacement in the output file -> ");
                        String replaceWord = Entry.scanner.nextLine();
                        System.out.println("The word \"" + eachWord + "\" " + "will be changed to \"" + replaceWord + "\".\n");
                        Entry.newWords.add(replaceWord);
                    }
                }
            }
            else {
                Entry.newWords.add(eachWord);
            }
        }
    }

    /**
     * Writes every word in the static instance "newWords" to the output file
     * @param givenOutFileName is the designated name of the output file
     */
    public static void writeFile(String givenOutFileName) {
        try {
            PrintWriter outFile = new PrintWriter(givenOutFileName);
            for (String newWord: Entry.newWords) {
                outFile.print(newWord + " ");
            }
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("The spell-checked file " + givenOutFileName + " was created.");
    }

    /**
     * Main program
     */
    public static void begin() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");

        String inputFileName = Entry.handleFileName();

        String outFileName = Entry.handleOutFileName(inputFileName);

        Entry.storeFileWords(inputFileName);

        Entry.spellCheck(recommender);

        Entry.writeFile(outFileName);

        System.out.println("Spell Check finished!");

        Entry.scanner.close();
    }

    public static void main(String[] args) {
        Entry.begin();
    }
}
