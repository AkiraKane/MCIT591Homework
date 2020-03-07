package assignment_5;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileIO.Data data = FileIO.handleFlightData();
        String solutionOne = Solution.questionOne(data);

    }
}
