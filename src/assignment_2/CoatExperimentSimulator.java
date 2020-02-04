package assignment_2;

public class CoatExperimentSimulator {
    int numberOfPeople;

    /**
     * Constructor
     * @param numPpl
     */
    public CoatExperimentSimulator(int numPpl)
    {
        this.numberOfPeople = numPpl;
    }

    public int numPplWhoGotTheirCoat(int[] permutation)
    {
        int count = 0;
        for (int i = 0; i < this.numberOfPeople; i++)
        {
            if (permutation[i] == i + 1)
            {
                count++;
            }
        }
        return count;
    }

    public int[] simulateCoatExperiment(int iterations)
    {
        int[] results = new int[iterations];
        for (int i = 0; i < iterations; i++)
        {
            results[i] = this.numPplWhoGotTheirCoat(RandomOrderGenerator.getRandomOrder(this.numberOfPeople));
        }
        return results;
    }

    public double answerToQuestionA(int[] results)
    {
        int answerZero = 0;
        for (int i = 0; i < results.length; i++)
        {
            if (results[i] == 0)
            {
                answerZero++;
            }
        }
        return (float) answerZero / results.length;
    }

    public double answerToQuestionB(int[] results)
    {
        int sum = 0;
        for (int i = 0; i < results.length; i++)
        {
            sum += results[i];
        }
        return (float) sum / results.length;
    }

    public static void main(String[] args) {
        CoatExperimentSimulator simulator = new CoatExperimentSimulator(25);
        int[] results = simulator.simulateCoatExperiment(100000);
        double probability = simulator.answerToQuestionA(results);
        System.out.println(probability);
        double average = simulator.answerToQuestionB(results);
        System.out.println(average);
        double estimate = 1 / probability;
        System.out.println(estimate);
    }
}
