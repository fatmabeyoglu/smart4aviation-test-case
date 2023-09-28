package com.smart4aviation.flighttestcase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.smart4aviation.flighttestcase.dataModel.CargoEntity;
import com.smart4aviation.flighttestcase.dataModel.FlightEntity;
import com.smart4aviation.flighttestcase.dataModel.ItemEntity;
import com.smart4aviation.flighttestcase.repository.ICargoRepository;
import com.smart4aviation.flighttestcase.service.AirportServiceImpl;
import com.smart4aviation.flighttestcase.viewModel.AirportViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class AirportServiceImplTest {

    @Mock
    private ICargoRepository cargoRepository;

    @InjectMocks
    private AirportServiceImpl airportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByParamsWithMatchingFlights() throws IOException, ParseException {
        // Arrange
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date testDate = dateFormat.parse("2023-09-28");

        List<FlightEntity> flights = new ArrayList<>();
        FlightEntity flight1 = new FlightEntity();
        flight1.setFlightId(1);
        flight1.setDepartureDate(testDate);
        flight1.setDepartureAirportIATACode("JFK");
        flight1.setArrivalAirportIATACode("LAX");
        flights.add(flight1);

        List<CargoEntity> cargos = new ArrayList<>();
        CargoEntity cargo1 = new CargoEntity();
        cargo1.setFlightId(1);
        ItemEntity item1 = new ItemEntity();
        item1.setPieces(10);
        ItemEntity item2 = new ItemEntity();
        item2.setPieces(5);
        cargo1.getBaggage().add(item1);
        cargo1.getBaggage().add(item2);
        cargos.add(cargo1);

        when(cargoRepository.findAllFlights()).thenReturn(flights);
        when(cargoRepository.findAllCargos()).thenReturn(cargos);

        // Act
        AirportViewModel result = airportService.findByParams("JFK", "2023-09-28");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getArrivalBaggageCount());
        assertEquals(0, result.getArrivalFlightCount());
        assertEquals(15, result.getDepartureBaggageCount());
        assertEquals(1, result.getDepartureFlightCount());
    }

    @Test
    public void testFindByParamsNoMatchingFlights() throws IOException {
        // Arrange
        List<FlightEntity> flights = new ArrayList<>();
        List<CargoEntity> cargos = new ArrayList<>();

        when(cargoRepository.findAllFlights()).thenReturn(flights);
        when(cargoRepository.findAllCargos()).thenReturn(cargos);

        // Act
        AirportViewModel result = airportService.findByParams("JFK", "2023-09-28");

        // Assert
        assertNull(result);
    }
}
