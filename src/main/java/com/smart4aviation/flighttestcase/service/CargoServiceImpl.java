package com.smart4aviation.flighttestcase.service;

import com.smart4aviation.flighttestcase.dataModel.CargoEntity;
import com.smart4aviation.flighttestcase.dataModel.FlightEntity;
import com.smart4aviation.flighttestcase.dataModel.ItemEntity;
import com.smart4aviation.flighttestcase.repository.ICargoRepository;
import com.smart4aviation.flighttestcase.viewModel.CargoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements ICargoService {
    ICargoRepository cargoRepository;
    SimpleDateFormat dateFormat;

    @Autowired
    public CargoServiceImpl(ICargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public CargoViewModel findByParams(int flightNumber, String date) throws IOException {
        List<FlightEntity> flights = cargoRepository.findAllFlights();
        List<CargoEntity> cargos = cargoRepository.findAllCargos();

        if (flights != null) {
            Optional<FlightEntity> filteredFlight = flights.stream()
                    .filter(flight -> flight.getFlightNumber() == flightNumber)
                    .filter(flight -> {
                        String flightDateStr = dateFormat.format(flight.getDepartureDate());
                        return flightDateStr.equals(date);
                    })
                    .findFirst();

            if (filteredFlight.isPresent() && cargos != null) {
                FlightEntity flightEntity = filteredFlight.get();

                Optional<CargoEntity> filteredCargo = cargos.stream()
                        .filter(cargo -> cargo.getFlightId() == flightEntity.getFlightId())
                        .findFirst();
                if (filteredCargo.isPresent()) {
                    CargoEntity cargoEntity = filteredCargo.get();
                    double sumOfCargoWeights = cargoEntity.getCargo().stream()
                            .mapToDouble(ItemEntity::getWeight)
                            .sum();
                    double sumOfBaggageWeights = cargoEntity.getBaggage().stream()
                            .mapToDouble(ItemEntity::getWeight) // Extract flightId values as integers
                            .sum();
                    double sumOfWeights = sumOfBaggageWeights + sumOfCargoWeights;

                    return new CargoViewModel(flightEntity.getFlightId(), sumOfCargoWeights, sumOfBaggageWeights, sumOfWeights);
                }
            }
        }
        return null;
    }
}
