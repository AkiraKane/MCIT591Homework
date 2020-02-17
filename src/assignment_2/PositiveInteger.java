package assignment_2;

import javafx.geometry.Pos;

import java.util.ArrayList;

public class PositiveInteger {
    int num;

    public PositiveInteger(int number)
    {
        this.num = number;
    }

    public boolean isPerfect()
    {
        if (this.num == 1)
        {
            return false;
        }
        else
        {
            ArrayList<Integer> factors = new ArrayList<Integer>();
            for (int i = 1; i < this.num; i++)
            {
                if (this.num % i == 0)
                {
                    factors.add(i);
                }
            }
            int sumFactors = 0;
            for (int factor: factors)
            {
                sumFactors += factor;
            }

            return sumFactors == this.num;
        }
    }

    public boolean isAbundant()
    {
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 1; i < this.num; i++)
        {
            if (this.num % i == 0)
            {
                factors.add(i);
            }
        }
        int sumFactors = 0;
        for (int factor: factors)
        {
            sumFactors += factor;
        }
        return sumFactors > this.num;
    }

    public boolean isNarcissistic()
    {
        if (this.num < 10)
        {
            return true;
        }
        else
        {
            int number = this.num;
            int numberOfDigits = 0;

            while (number != 0)
            {
                number = number / 10;
                numberOfDigits++;
            }

            number = this.num;
            ArrayList<Integer> digits = new ArrayList<>();
            for (int i = numberOfDigits - 1; i >= 0; i--)
            {
                int tens = 1;
                for (int j = 0; j < i; j++)
                {
                    tens *= 10;
                }
                digits.add(number / tens);
                number = number % tens;
            }

            int sum = 0;
            for (int digit : digits)
            {
                int powerSum = 1;
                for (int i = 0; i < numberOfDigits; i++)
                {
                    powerSum *=digit;
                }
                sum += powerSum;
            }
            System.out.println(sum);
            return sum == this.num;
        }
    }
    
}
