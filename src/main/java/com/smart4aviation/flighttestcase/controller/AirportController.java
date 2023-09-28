package com.smart4aviation.flighttestcase.controller;

import com.smart4aviation.flighttestcase.service.IAirportService;
import com.smart4aviation.flighttestcase.viewModel.AirportViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AirportController {
    IAirportService airportService;

    @Autowired
    public void setAirportService(IAirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airport/{iataAirportCode}/{date}")
    public ResponseEntity<AirportViewModel> find(@PathVariable String iataAirportCode, @PathVariable String date) throws IOException {
        // TODO: Input validation.
        AirportViewModel result = airportService.findByParams(iataAirportCode, date);

        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
