package assignment_5;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * This class is all about handling the files.
 */
public class FileIO {

    /**
     * This class is the self-designed data structure for the data
     */
    public static class Data {
        private ArrayList<FlightInfo> fliedFlights;
        private ArrayList<FlightInfo> canceledFlights;

        public Data(ArrayList<FlightInfo> fliedFlights, ArrayList<FlightInfo> canceledFlights) {
            this.fliedFlights = fliedFlights;
            this.canceledFlights = canceledFlights;
        }

        public ArrayList<FlightInfo> getFliedFlights() {
            return fliedFlights;
        }

        public ArrayList<FlightInfo> getCanceledFlights() {
            return canceledFlights;
        }
    }

    /**
     * Handles the raw flight records
     * @return flight records as in the self-designed data structure
     * @throws FileNotFoundException
     */
    public static Data handleFlightData() throws FileNotFoundException {
        ArrayList<FlightInfo> fliedFlights = new ArrayList<>();
        ArrayList<FlightInfo> canceledFlights = new ArrayList<>();
        File inputFile = new File("src\\assignment_5\\flights.csv");
        Scanner in = new Scanner(inputFile);
        in.nextLine();
        while (in.hasNextLine()) {
            String singleFlightInfo = in.nextLine();
            String[] info = singleFlightInfo.split(",");
            if (info[17].equals("1"))
                canceledFlights.add(new FlightInfo(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15], info[16], info[17], info[18], info[19], info[20], info[21]));
            else {
                int missCount = 0;
                for (int i = 0; i < 22; i++) {
                    if (info[i].equals("") && i != 18) missCount++;
                }
                if (missCount == 0) fliedFlights.add(new FlightInfo(info[0], info[1], info[2], info[3], info[4], info[5], info[6], info[7], info[8], info[9], info[10], info[11], info[12], info[13], info[14], info[15], info[16], info[17], info[18], info[19], info[20], info[21]));
            }
        }
        return new Data(fliedFlights, canceledFlights);
    }
}
