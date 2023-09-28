package com.smart4aviation.flighttestcase.dataModel;

import java.util.ArrayList;
import java.util.List;

public class CargoEntity {
    private int flightId;
    private List<ItemEntity> baggage;
    private List<ItemEntity> cargo;

    public CargoEntity() {
        this.baggage = new ArrayList<>();
        this.cargo = new ArrayList<>();;
    }

    public CargoEntity(int flightId, List<ItemEntity> baggage, List<ItemEntity> cargo) {
        this.flightId = flightId;
        this.baggage = baggage;
        this.cargo = cargo;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public List<ItemEntity> getBaggage() {
        return baggage;
    }

    public void setBaggage(List<ItemEntity> baggage) {
        this.baggage = baggage;
    }

    public List<ItemEntity> getCargo() {
        return cargo;
    }

    public void setCargo(List<ItemEntity> cargo) {
        this.cargo = cargo;
    }
}
