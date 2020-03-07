package assignment_5;

public class FlightInfo {
    private String dayOfMonth;
    private String dayOfWeek;
    private String FlightDate;
    private String Carrier;
    private String tailNum;
    private String oriAirportID;
    private String oriAirport;
    private String oriState;
    private String desAirportID;
    private String desAirport;
    private String desState;
    private String depTime;
    private String depDelay;
    private String wheelsOff;
    private String wheelsOn;
    private String arrTime;
    private String arrDelay;
    private String canceled;
    private String cancelCode;
    private String diverted;
    private String airTime;
    private String distance;

    public FlightInfo() {
        dayOfMonth = "";
        dayOfWeek = "";
        FlightDate = "";
        Carrier = "";
        tailNum = "";
        oriAirportID = "";
        oriAirport = "";
        oriState = "";
        desAirportID = "";
        desAirport = "";
        desState = "";
        depTime = "";
        depDelay = "";
        wheelsOff = "";
        wheelsOn = "";
        arrTime = "";
        arrDelay = "";
        canceled = "";
        cancelCode = "";
        diverted = "";
        airTime = "";
        distance = "";
    }

    public FlightInfo(String dayOfMonth, String dayOfWeek, String flightDate, String carrier, String tailNum, String oriAirportID, String oriAirport, String oriState, String desAirportID, String desAirport, String desState, String depTime, String depDelay, String wheelsOff, String wheelsOn, String arrTime, String arrDelay, String canceled, String cancelCode, String diverted, String airTime, String distance) {
        this.dayOfMonth = dayOfMonth;
        this.dayOfWeek = dayOfWeek;
        FlightDate = flightDate;
        Carrier = carrier;
        this.tailNum = tailNum;
        this.oriAirportID = oriAirportID;
        this.oriAirport = oriAirport;
        this.oriState = oriState;
        this.desAirportID = desAirportID;
        this.desAirport = desAirport;
        this.desState = desState;
        this.depTime = depTime;
        this.depDelay = depDelay;
        this.wheelsOff = wheelsOff;
        this.wheelsOn = wheelsOn;
        this.arrTime = arrTime;
        this.arrDelay = arrDelay;
        this.canceled = canceled;
        this.cancelCode = cancelCode;
        this.diverted = diverted;
        this.airTime = airTime;
        this.distance = distance;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getFlightDate() {
        return FlightDate;
    }

    public String getCarrier() {
        return Carrier;
    }

    public String getTailNum() {
        return tailNum;
    }

    public String getOriAirportID() {
        return oriAirportID;
    }

    public String getOriAirport() {
        return oriAirport;
    }

    public String getOriState() {
        return oriState;
    }

    public String getDesAirportID() {
        return desAirportID;
    }

    public String getDesAirport() {
        return desAirport;
    }

    public String getDesState() {
        return desState;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getDepDelay() {
        return depDelay;
    }

    public String getWheelsOff() {
        return wheelsOff;
    }

    public String getWheelsOn() {
        return wheelsOn;
    }

    public String getArrTime() {
        return arrTime;
    }

    public String getArrDelay() {
        return arrDelay;
    }

    public String getCanceled() {
        return canceled;
    }

    public String getCancelCode() {
        return cancelCode;
    }

    public String getDiverted() {
        return diverted;
    }

    public String getAirTime() {
        return airTime;
    }

    public String getDistance() {
        return distance;
    }
}
