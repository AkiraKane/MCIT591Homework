package assignment_5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {

    public static class StringDouble implements Comparable<StringDouble> {
        private String str;
        private double num;

        public StringDouble(String carrier, double cancelPercent) {
            this.str = carrier;
            this.num = cancelPercent;
        }

        public String getStr() {
            return str;
        }

        public double getNum() {
            return num;
        }

        public int compareTo(StringDouble o) {
            if (this.num > o.num) return -1;
            else return 1;
        }

    }

    public static class StringInt implements Comparable<StringInt> {
        private String str;
        private int num;

        public StringInt(String str, int num) {
            this.str = str;
            this.num = num;
        }

        public String getStr() {
            return str;
        }

        public int getNum() {
            return num;
        }

        //
        public int compareTo(StringInt o) {
            return o.num - this.num;
        }
    }

    public static String questionOne(FileIO.Data data) {
        HashMap<String, Integer> flightNums = new HashMap<>();
        HashMap<String, Integer> cancelNums = new HashMap<>();

        for (FlightInfo flight: data.getFliedFlights()) {
            if (!flightNums.containsKey(flight.getCarrier())) flightNums.put(flight.getCarrier(), 1);
            else flightNums.put(flight.getCarrier(), flightNums.get(flight.getCarrier()) + 1);
        }
        for (FlightInfo flight: data.getCanceledFlights()) {
            if (!cancelNums.containsKey(flight.getCarrier())) cancelNums.put(flight.getCarrier(), 1);
            else cancelNums.put(flight.getCarrier(), cancelNums.get(flight.getCarrier()) + 1);
        }
        StringDouble[] percentages = new StringDouble[flightNums.size()];
        int i = 0;
        for (String carrier: flightNums.keySet()) {
            percentages[i] = new StringDouble(carrier, (double) cancelNums.get(carrier) / (cancelNums.get(carrier) + flightNums.get(carrier)));
            i++;
        }
        Arrays.sort(percentages);

        return percentages[0].getStr() +
                "," +
                Double.toString(percentages[0].getNum() * 100).substring(0, 4) +
                "%";
    }

    // only consider canceled flight
    public static String questionTwo(FileIO.Data data) {
        HashMap<String, Integer> codeCount = new HashMap<>();
        for (FlightInfo info: data.getCanceledFlights()) {
            if (!info.getCancelCode().equals("")) {
                if (!codeCount.containsKey(info.getCancelCode())) codeCount.put(info.getCancelCode(), 1);
                else codeCount.put(info.getCancelCode(), codeCount.get(info.getCancelCode()) + 1);
            }
        }
        StringInt[] codeCounts = new StringInt[codeCount.size()];
        int i = 0;
        for (String code: codeCount.keySet()) {
            codeCounts[i] = new StringInt(code, codeCount.get(code));
            i++;
        }
        Arrays.sort(codeCounts);

        return codeCounts[0].getStr();
    }

    // didnt' count canceled flight
    public static String questionThree(FileIO.Data data) {
        HashMap<String, Integer> flyDistance = new HashMap<>();
        for (FlightInfo flight: data.getFliedFlights()) {
            if (!flyDistance.containsKey(flight.getTailNum())) flyDistance.put(flight.getTailNum(), Integer.parseInt(flight.getDistance()));
            else flyDistance.put(flight.getTailNum(), flyDistance.get(flight.getTailNum()) + Integer.parseInt(flight.getDistance()));
        }

        StringInt[] distanceArray = new StringInt[flyDistance.size()];
        int i = 0;
        for (String tailNum: flyDistance.keySet()) {
            distanceArray[i] = new StringInt(tailNum, flyDistance.get(tailNum));
            i++;
        }
        Arrays.sort(distanceArray);
        System.out.println("PAUSE");
        return distanceArray[0].getStr();
    }

    // both in and out
    public static String questionFour(FileIO.Data data) {
        HashMap<String, Integer> airportAppearance = new HashMap<>();
        for (FlightInfo flight: data.getFliedFlights()) {
            if (!airportAppearance.containsKey(flight.getOriAirportID())) airportAppearance.put(flight.getOriAirportID(), 1);
            else airportAppearance.put(flight.getOriAirportID(), airportAppearance.get(flight.getOriAirportID()) + 1);
            if (!airportAppearance.containsKey(flight.getDesAirportID())) airportAppearance.put(flight.getDesAirportID(), 1);
            else airportAppearance.put(flight.getDesAirportID(), airportAppearance.get(flight.getDesAirportID()) + 1);
        }

        StringInt[] airportArray = new StringInt[airportAppearance.size()];
        int i = 0;
        for (String airportID: airportAppearance.keySet()) {
            airportArray[i] = new StringInt(airportID, airportAppearance.get(airportID));
            i++;
        }
        Arrays.sort(airportArray);
        System.out.println("PAUSE");
        return airportArray[0].getStr();
    }

    public static String questionFive(FileIO.Data data) {
        HashMap<String, Integer> departure = new HashMap<>();
        HashMap<String, Integer> arrival = new HashMap<>();
        HashMap<String, Integer> difference = new HashMap<>();
        ArrayList<String> airportIDs = new ArrayList<>();
        for (FlightInfo flight: data.getFliedFlights()) {
            if (!departure.containsKey(flight.getOriAirportID())) departure.put(flight.getOriAirportID(), 1);
            else departure.put(flight.getOriAirportID(), departure.get(flight.getOriAirportID()) + 1);
            if (!arrival.containsKey(flight.getDesAirportID())) arrival.put(flight.getDesAirportID(), 1);
            else arrival.put(flight.getDesAirportID(), arrival.get(flight.getDesAirportID()) + 1);
            if (!airportIDs.contains(flight.getOriAirportID())) airportIDs.add(flight.getOriAirportID());
            if (!airportIDs.contains(flight.getDesAirportID())) airportIDs.add(flight.getDesAirportID());
        }

        for (String airportID: airportIDs) {
            if (departure.containsKey(airportID) && arrival.containsKey(airportID)) difference.put(airportID, departure.get(airportID) - arrival.get(airportID));
            else if (departure.containsKey(airportID)) difference.put(airportID, departure.get(airportID));
            else difference.put(airportID, -arrival.get(airportID));
        }
        StringInt[] airportArray = new StringInt[difference.size()];
        int i = 0;
        for (String airportID: difference.keySet()) {
            airportArray[i] = new StringInt(airportID, difference.get(airportID));
            i++;
        }
        Arrays.sort(airportArray);
        System.out.println("PAUSE");
        return airportArray[0].getStr();
    }

    public static String questionSix(FileIO.Data data) {
        HashMap<String, Integer> departure = new HashMap<>();
        HashMap<String, Integer> arrival = new HashMap<>();
        HashMap<String, Integer> difference = new HashMap<>();
        ArrayList<String> airportIDs = new ArrayList<>();
        for (FlightInfo flight: data.getFliedFlights()) {
            if (!departure.containsKey(flight.getOriAirportID())) departure.put(flight.getOriAirportID(), 1);
            else departure.put(flight.getOriAirportID(), departure.get(flight.getOriAirportID()) + 1);
            if (!arrival.containsKey(flight.getDesAirportID())) arrival.put(flight.getDesAirportID(), 1);
            else arrival.put(flight.getDesAirportID(), arrival.get(flight.getDesAirportID()) + 1);
            if (!airportIDs.contains(flight.getOriAirportID())) airportIDs.add(flight.getOriAirportID());
            if (!airportIDs.contains(flight.getDesAirportID())) airportIDs.add(flight.getDesAirportID());
        }

        for (String airportID: airportIDs) {
            if (departure.containsKey(airportID) && arrival.containsKey(airportID)) difference.put(airportID, -departure.get(airportID) + arrival.get(airportID));
            else if (departure.containsKey(airportID)) difference.put(airportID, -departure.get(airportID));
            else difference.put(airportID, arrival.get(airportID));
        }
        StringInt[] airportArray = new StringInt[difference.size()];
        int i = 0;
        for (String airportID: difference.keySet()) {
            airportArray[i] = new StringInt(airportID, difference.get(airportID));
            i++;
        }
        Arrays.sort(airportArray);
        System.out.println("PAUSE");
        return airportArray[0].getStr();
    }

    public static int questionSeven(FileIO.Data data) {
        int count = 0;
        for (FlightInfo info: data.getFliedFlights()) {
            if (info.getCarrier().equals("AA")) {
                int depDelay = Integer.parseInt(info.getDepDelay());
                int arrDelay = Integer.parseInt(info.getArrDelay());
                if (depDelay >= 60 || arrDelay >= 60) count++;
            }
        }
        return count;
    }

    public static String questionEight(FileIO.Data data) {
        int delay = 0;
        FlightInfo result = new FlightInfo();
        for (FlightInfo info: data.getFliedFlights()) {
            if (Integer.parseInt(info.getArrDelay()) <= 0 && Integer.parseInt(info.getDepDelay()) > delay) {
                delay = Integer.parseInt(info.getDepDelay());
                result = info;
            }
        }

        return result.getDayOfMonth() +
                "," +
                result.getDepDelay() +
                "," +
                result.getTailNum();
    }

}
