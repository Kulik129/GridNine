package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;
import com.gridnine.testing.modules.flights.Segment;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация фильтра, выполняющего фильтрацию перелетов, чье общее время, проведенное на земле,
 * не превышает два часа.
 */
public class TotalGroundTimeFilter implements FlightFilter {
    /**
     * Фильтрует список перелетов, исключая те, у которых общее время на земле не превышает два часа.
     *
     * @param flights Список перелетов для фильтрации.
     * @return Список перелетов, прошедших фильтрацию.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        try {
            filteredFlights = flights.stream()
                    .filter(it -> calculateTotalGroundTime(it) <= Duration.ofHours(2).toHours())
                    .collect(Collectors.toList());
        } catch (DateTimeException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException ex) {
            System.err.println("Ошибка при фильтрации полетов исключая те, у которых общее время на земле не превышает два часа. " + ex.getMessage());
        }
        return filteredFlights;
    }

    /**
     * Подсчет общего времени на земле.
     *
     * @param flight Полет для которого будет произведен подсчет общего времени на земле.
     * @return общее время проведенное на земле.
     */
    private long calculateTotalGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalGroundTime = 0;
        for (int i = 1; i < segments.size(); i++) {
            try {
                LocalDateTime previousArrival = segments.get(i - 1).getArrivalDate();
                LocalDateTime currentDeparture = segments.get(i).getDepartureDate();
                totalGroundTime += Duration.between(previousArrival, currentDeparture).toHours();
            } catch (DateTimeException ex) {
                System.err.println("Ошибка при вычислении времени на земле для сегмента: " + ex.getMessage());
                totalGroundTime += 2;
            }
        }
        return totalGroundTime;
    }
}
