package assignment_5;

import java.io.FileNotFoundException;

/**
 * This class is the entry point of the program.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileIO.Data data = FileIO.handleFlightData();
        String solutionOne = Solution.questionOne(data);
        String solutionTwo = Solution.questionTwo(data);
        String solutionThree = Solution.questionThree(data);
        String solutionFour = Solution.questionFour(data);
        String solutionFive = Solution.questionFive(data);
        String solutionSix = Solution.questionSix(data);
        int solutionSeven = Solution.questionSeven(data);
        String solutionEight = Solution.questionEight(data);
        String solutionNine = Solution.questionNine(data);

        FormattedOutput output = new FormattedOutput();
        output.addAnswer(1, solutionOne);
        output.addAnswer(2, solutionTwo);
        output.addAnswer(3, solutionThree);
        output.addAnswer(4, solutionFour);
        output.addAnswer(5, solutionFive);
        output.addAnswer(6, solutionSix);
        output.addAnswer(7, solutionSeven);
        output.addAnswer(8, solutionEight);
        output.addAnswer(9, solutionNine);
        output.writeAnswers();
        System.out.println("END");
    }
}
