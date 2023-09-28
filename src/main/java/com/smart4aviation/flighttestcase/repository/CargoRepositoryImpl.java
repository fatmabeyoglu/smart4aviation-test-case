package com.smart4aviation.flighttestcase.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart4aviation.flighttestcase.dataModel.CargoEntity;
import com.smart4aviation.flighttestcase.dataModel.FlightEntity;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class CargoRepositoryImpl implements ICargoRepository {
    @Override
    public List<FlightEntity> findAllFlights() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<FlightEntity> flights = null;

        try {
            // TODO: Use in memory database instead reading from a json file.
            ClassPathResource resource = new ClassPathResource("test-data-flight.json");
            flights = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<FlightEntity>>() {});
        } catch (IOException e) {
            // TODO: Log exceptions.
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public List<CargoEntity> findAllCargos() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<CargoEntity> cargos = null;

        try {
            // TODO: Use in memory database instead reading from a json file.
            ClassPathResource resource = new ClassPathResource("test-data-cargo.json");
            cargos = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<CargoEntity>>() {});
        } catch (IOException e) {
            // TODO: Log exceptions.
            e.printStackTrace();
        }
        return cargos;
    }
}
