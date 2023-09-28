package com.smart4aviation.flighttestcase.controller;

import com.smart4aviation.flighttestcase.service.ICargoService;
import com.smart4aviation.flighttestcase.viewModel.CargoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CargoController {
    private final ICargoService cargoService;

    @Autowired
    public CargoController(ICargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/cargo/{flightNumber}/{date}")
    public ResponseEntity<CargoViewModel> find(@PathVariable int flightNumber, @PathVariable String date) throws IOException {
        // TODO: Input validation.
        var result = cargoService.findByParams(flightNumber, date);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
