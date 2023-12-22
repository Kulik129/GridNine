package com.gridnine.testing;


import com.gridnine.testing.modules.filters.*;
import com.gridnine.testing.modules.flights.Flight;
import com.gridnine.testing.modules.flights.FlightBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("\n________ Исключен рейс с вылетом до текущего момента времени ________");
        FlightFilter departureBeforeNow = new DepartureBeforeNowFilter();
        List<Flight> departureFilter = departureBeforeNow.filter(flights);
        departureFilter.forEach(System.out::println);

        System.out.println("\n________ Исключен рейс с имеющимся сегментом датой прилёта раньше даты вылета ________");
        FlightFilter arrivalBeforeDeparture = new ArrivalBeforeDepartureFilter();
        List<Flight> arrivalFilter = arrivalBeforeDeparture.filter(flights);
        arrivalFilter.forEach(System.out::println);

        System.out.println("\n________ Исключен рейс с общим временем, проведённым на земле превышающим два часа  ________");
        FlightFilter totalGroundTime = new TotalGroundTimeFilter();
        List<Flight> totalGroundFilter = totalGroundTime.filter(flights);
        totalGroundFilter.forEach(System.out::println);
    }
}