package com.smart4aviation.flighttestcase.service;

import com.smart4aviation.flighttestcase.viewModel.AirportViewModel;

import java.io.IOException;

public interface IAirportService {
    AirportViewModel findByParams(String iataAirportCode, String date) throws IOException;
}
