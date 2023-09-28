package com.smart4aviation.flighttestcase.service;

import com.smart4aviation.flighttestcase.viewModel.CargoViewModel;

import java.io.IOException;

public interface ICargoService {
    CargoViewModel findByParams(int flightNumber, String date) throws IOException;
}
