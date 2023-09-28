package com.smart4aviation.flighttestcase.repository;

import com.smart4aviation.flighttestcase.dataModel.CargoEntity;
import com.smart4aviation.flighttestcase.dataModel.FlightEntity;

import java.io.IOException;
import java.util.List;

public interface ICargoRepository {
    List<FlightEntity> findAllFlights() throws IOException;
    List<CargoEntity> findAllCargos();
}
