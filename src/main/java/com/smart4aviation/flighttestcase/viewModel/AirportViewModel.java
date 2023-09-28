package com.smart4aviation.flighttestcase.viewModel;

public class AirportViewModel {
    private int departureFlightCount;
    private int arrivalFlightCount;
    private int departureBaggageCount;
    private int arrivalBaggageCount;

    public AirportViewModel() {
    }

    public AirportViewModel(int departureFlightCount, int arrivalFlightCount, int departureBaggageCount, int arrivalBaggageCount) {
        this.departureFlightCount = departureFlightCount;
        this.arrivalFlightCount = arrivalFlightCount;
        this.departureBaggageCount = departureBaggageCount;
        this.arrivalBaggageCount = arrivalBaggageCount;
    }

    public int getDepartureFlightCount() {
        return departureFlightCount;
    }

    public void setDepartureFlightCount(int departureFlightCount) {
        this.departureFlightCount = departureFlightCount;
    }

    public int getArrivalFlightCount() {
        return arrivalFlightCount;
    }

    public void setArrivalFlightCount(int arrivalFlightCount) {
        this.arrivalFlightCount = arrivalFlightCount;
    }

    public int getDepartureBaggageCount() {
        return departureBaggageCount;
    }

    public void setDepartureBaggageCount(int departureBaggageCount) {
        this.departureBaggageCount = departureBaggageCount;
    }

    public int getArrivalBaggageCount() {
        return arrivalBaggageCount;
    }

    public void setArrivalBaggageCount(int arrivalBaggageCount) {
        this.arrivalBaggageCount = arrivalBaggageCount;
    }
}
