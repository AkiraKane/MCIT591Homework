package assignment_4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Entry {
    private static ArrayList<String> fileWords = new ArrayList<>();
    private static ArrayList<String> newWords = new ArrayList<>();

    public static String handleFileName () {
        Scanner inScanner = new Scanner(System.in);
        System.out.print("Please provide the name of your file -> ");
        String fileName = inScanner.nextLine();
        inScanner.close();
        return fileName;
    }

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
    public static void storeFileWords(String givenName) {
        File userFile = new File(givenName);
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
                    if (givenRecommender.getDictionary().get(i).word.equals(eachWord)) {
                        containWord = true;
                        break;
                    }
                }
            }

            if (!containWord) {
                ArrayList<String> suggestions = givenRecommender.getWordSuggestions(eachWord, 2, 0.5, 5);
                System.out.println("The word \"" + eachWord + "\" was probably misspelled.");
                Scanner choiceScan = new Scanner(System.in);
                if (suggestions.size() == 0) {
                    System.out.println("There are 0 suggestions in our dictionary for this word.");
                    System.out.print("Press 'a' for accept as is, 't' for type in manually -> ");

                    String choice = choiceScan.nextLine();
                    while ((!choice.equals("a")) && (!choice.equals("t"))) {
                        System.out.print("Invalid input, please retry 'a' or 't' -> ");
                        choice = choiceScan.nextLine();
                    }
                    if (choice.equals("a")) {
                        System.out.println("The word \"" + eachWord + "\" " + "will remain unchanged.\n");
                        Entry.newWords.add(eachWord);
                    }
                    else {
                        System.out.print("Please type the word that will be used as the replacement in the output file -> ");
                        String replaceWord = choiceScan.nextLine();
                        System.out.println("The word \"" + eachWord + "\" " + "will be changed to \"" + replaceWord + "\".\n");
                        Entry.newWords.add(replaceWord);
                    }
                }
                else {
                    System.out.println("The following suggestions are available:");
                    System.out.println(givenRecommender.prettyPrint(suggestions));

                    System.out.print("Press 'r' for replace, 'a' for accept as is, 't' for type in manually -> ");
                    String choice = choiceScan.nextLine();
                    while ((!choice.equals("r")) && (!choice.equals("a")) && (!choice.equals("t"))) {
                        System.out.print("Invalid input, please retry 'r', 'a' or 't' -> ");
                        choice = choiceScan.nextLine();
                    }
                    if (choice.equals("r")) {
                        System.out.println("Your word will now be replaced with one of the suggestions.");
                        System.out.print("Enter the number corresponding to the word that you want to use for replacement -> ");
                        int wordNumber = choiceScan.nextInt();
                        while ((wordNumber > 4) || (wordNumber < 0)) {
                            System.out.print("Invalid input, please re-enter the correct number -> ");
                            wordNumber = choiceScan.nextInt();
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
                        String replaceWord = choiceScan.nextLine();
                        System.out.println("The word \"" + eachWord + "\" " + "will be changed to \"" + replaceWord + "\".\n");
                        Entry.newWords.add(replaceWord);
                    }
                }
                choiceScan.close();
            }
            else {
                Entry.newWords.add(eachWord);
            }
        }
    }

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

    public static void begin() {
        WordRecommender recommender = new WordRecommender("engDictionary.txt");

        String inputFileName = Entry.handleFileName();

        String outFileName = Entry.handleOutFileName(inputFileName);

        Entry.storeFileWords(inputFileName);

        Entry.spellCheck(recommender);

        Entry.writeFile(outFileName);

        System.out.println("Spell Check finished!");
    }

    public static void main(String[] args) {
        Entry.begin();
    }
}
