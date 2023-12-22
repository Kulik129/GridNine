package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;
import com.gridnine.testing.modules.flights.FlightBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FiltersTest {
    private static List<Flight> flights;

    @BeforeAll
    public static void init() {
        flights = FlightBuilder.createFlights();
    }

    @Test
    void arrivalBeforeDepartureFilterTest() {
        FlightFilter arrivalBeforeDeparture = new ArrivalBeforeDepartureFilter();
        List<Flight> arrivalFilter = arrivalBeforeDeparture.filter(flights);

        assertEquals(5, arrivalFilter.size());
    }

    @Test
    void departureBeforeNowFilterTest() {
        FlightFilter departureBeforeNow = new DepartureBeforeNowFilter();
        List<Flight> departureFilter = departureBeforeNow.filter(flights);

        assertEquals(5, departureFilter.size());
    }

    @Test
    void MoreTwoHoursTimeFilterTest() {
        FlightFilter moreTwoHours = new MoreTwoHoursTimeFilter();
        List<Flight> moreTwoHr = moreTwoHours.filter(flights);

        assertEquals(1, moreTwoHr.size());
    }

    @Test
    void TotalGroundTimeFilterTest() {
        FlightFilter totalGroundTime = new TotalGroundTimeFilter();
        List<Flight> totalGroundFilter = totalGroundTime.filter(flights);

        assertEquals(4, totalGroundFilter.size());
    }
}