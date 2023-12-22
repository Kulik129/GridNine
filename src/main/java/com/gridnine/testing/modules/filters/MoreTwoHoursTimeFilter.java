package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация фильтра, выполняющего фильтрацию перелетов, чье общее время в пути превышает два часа.
 */
public class MoreTwoHoursTimeFilter implements FlightFilter {
    /**
     * Фильтрует список перелетов, исключая те, у которых общее время в пути превышает два часа.
     *
     * @param flights Список перелетов для фильтрации.
     * @return Список перелетов, прошедших фильтрацию.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> flightsFilter = new ArrayList<>();
        try {
            flightsFilter = flights.stream().filter(it -> it.getSegments().stream()
                    .anyMatch(seg -> {
                        LocalDateTime previousArrival = seg.getArrivalDate();
                        LocalDateTime currentDeparture = seg.getDepartureDate();
                        long hoursBetween = Duration.between(currentDeparture, previousArrival).toHours();
                        return hoursBetween > 2;
                    })).collect(Collectors.toList());
        } catch (DateTimeException | NullPointerException | IndexOutOfBoundsException |
                 IllegalArgumentException ex) {
            System.err.println("Ошибка при фильтрации полетов исключая те, у которых общее время в пути превышает два часа. " + ex.getMessage());
        }
        return flightsFilter;
    }
}
