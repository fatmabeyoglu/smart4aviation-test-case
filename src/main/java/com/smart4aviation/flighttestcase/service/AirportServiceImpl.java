package com.smart4aviation.flighttestcase.service;

import com.smart4aviation.flighttestcase.dataModel.CargoEntity;
import com.smart4aviation.flighttestcase.dataModel.FlightEntity;
import com.smart4aviation.flighttestcase.dataModel.ItemEntity;
import com.smart4aviation.flighttestcase.repository.ICargoRepository;
import com.smart4aviation.flighttestcase.viewModel.AirportViewModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements IAirportService {
    ICargoRepository cargoRepository;
    SimpleDateFormat dateFormat;

    public AirportServiceImpl(ICargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public AirportViewModel findByParams(String iataAirportCode, String date) throws IOException {

        List<FlightEntity> flights = cargoRepository.findAllFlights();
        List<CargoEntity> cargos = cargoRepository.findAllCargos();
        List<FlightEntity> filteredFlightsByDate = getFilteredFlightsByDate(date, flights);

        if (!filteredFlightsByDate.isEmpty()) {
            AirportViewModel airportViewModel = new AirportViewModel();
            List<FlightEntity> filteredArrivalFlights = getFilteredArrivalFlights(iataAirportCode, filteredFlightsByDate);
            List<FlightEntity> filteredDepartureFlights = getFilteredDepartureFlights(iataAirportCode, filteredFlightsByDate);
            if (!filteredArrivalFlights.isEmpty()) {
                airportViewModel.setArrivalBaggageCount(getSumOfBaggagePieces(cargos, filteredArrivalFlights));
                airportViewModel.setArrivalFlightCount(filteredArrivalFlights.size());
            }
            if (!filteredDepartureFlights.isEmpty()) {
                airportViewModel.setDepartureBaggageCount(getSumOfBaggagePieces(cargos, filteredDepartureFlights));
                airportViewModel.setDepartureFlightCount(filteredDepartureFlights.size());
            }
            return airportViewModel;
        } else {
            return null;
        }
    }

    private static int getSumOfBaggagePieces(List<CargoEntity> cargos, List<FlightEntity> filteredFlights) {
        int sumOfArrivalBaggagePieces = 0;
        for (FlightEntity flight : filteredFlights) {
            Optional<CargoEntity> filteredCargo = cargos.stream()
                    .filter(cargo -> cargo.getFlightId() == flight.getFlightId())
                    .findFirst();
            if (filteredCargo.isPresent()) {
                CargoEntity cargoEntity = filteredCargo.get();
                sumOfArrivalBaggagePieces = sumOfArrivalBaggagePieces + (int) cargoEntity.getBaggage().stream()
                        .mapToDouble(ItemEntity::getPieces)
                        .sum();
            }
        }
        return sumOfArrivalBaggagePieces;
    }

    private List<FlightEntity> getFilteredDepartureFlights(String iataAirportCode, List<FlightEntity> filteredFlightsByDate) {
        return filteredFlightsByDate.stream()
                .filter(flight -> flight.getDepartureAirportIATACode().equals(iataAirportCode))
                .collect(Collectors.toList());
    }

    private List<FlightEntity> getFilteredArrivalFlights(String iataAirportCode, List<FlightEntity> filteredFlightsByDate) {
        return filteredFlightsByDate.stream()
                .filter(flight -> flight.getArrivalAirportIATACode().equals(iataAirportCode))
                .collect(Collectors.toList());
    }

    private List<FlightEntity> getFilteredFlightsByDate(String date, List<FlightEntity> flights) {
        return flights.stream()
                .filter(flight -> dateFormat.format(flight.getDepartureDate()).equals(date))
                .collect(Collectors.toList());
    }
}
