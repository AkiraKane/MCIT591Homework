package assignment_5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {

    public static class CancelPercentage implements Comparable<CancelPercentage> {
        private String carrier = "";
        private double cancelPercent = 0.0;

        public CancelPercentage(String carrier, double cancelPercent) {
            this.carrier = carrier;
            this.cancelPercent = cancelPercent;
        }

        public String getCarrier() {
            return carrier;
        }

        public double getCancelPercent() {
            return cancelPercent;
        }

        public int compareTo(CancelPercentage o) {
            if (this.cancelPercent > o.cancelPercent) return -1;
            else return 1;
        }

    }

    public static String questionOne(FileIO.Data data) {
        HashMap<String, Integer> flightNums = new HashMap<>();
        HashMap<String, Integer> cancelNums = new HashMap<>();

        for (FlightInfo flight: data.fliedFlights) {
            if (!flightNums.containsKey(flight.getCarrier())) flightNums.put(flight.getCarrier(), 1);
            else flightNums.put(flight.getCarrier(), flightNums.get(flight.getCarrier()) + 1);
        }
        for (FlightInfo flight: data.canceledFlights) {
            if (!cancelNums.containsKey(flight.getCarrier())) cancelNums.put(flight.getCarrier(), 1);
            else cancelNums.put(flight.getCarrier(), cancelNums.get(flight.getCarrier()) + 1);
        }
        CancelPercentage[] percentages = new CancelPercentage[flightNums.size()];
        int i = 0;
        for (String carrier: flightNums.keySet()) {
            percentages[i] = new CancelPercentage(carrier, (double) cancelNums.get(carrier) / (cancelNums.get(carrier) + flightNums.get(carrier)));
            i++;
        }
        Arrays.sort(percentages);
        StringBuilder builder = new StringBuilder();
        builder.append(percentages[0].getCarrier());
        builder.append(",");
        builder.append(Double.toString(percentages[0].getCancelPercent() * 100).substring(0, 4));
        builder.append("%");

        return builder.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String res = Solution.questionOne(FileIO.handleFlightData());
        System.out.println("END");

    }
}
