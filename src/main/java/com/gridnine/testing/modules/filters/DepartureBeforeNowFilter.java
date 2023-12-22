package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация фильтра, выполняющего фильтрацию перелетов, у которых есть вылеты до текущего момента времени.
 */
public class DepartureBeforeNowFilter implements FlightFilter {
    /**
     * Фильтрует список перелетов, исключая те, у которых есть вылеты до текущего момента времени.
     *
     * @param flights Список перелетов для фильтрации.
     * @return Список перелетов, прошедших фильтрацию.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        List<Flight> flightsFilter = new ArrayList<>();
        try {
            flightsFilter = flights.stream()
                    .filter(it -> it.getSegments().stream()
                            .allMatch(seg -> seg.getDepartureDate().isAfter(now)))
                    .collect(Collectors.toList());
        } catch (DateTimeException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException ex) {
            System.err.println("Ошибка при фильтрации полетов исключая те, у которых есть вылеты до текущего момента времени. " + ex.getMessage());
        }
        return flightsFilter;
    }
}
