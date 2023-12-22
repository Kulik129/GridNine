package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация фильтра, выполняющего фильтрацию перелетов, у которых имеются сегменты
 * с датой прилета раньше даты вылета.
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter {
    /**
     * Фильтрует список перелетов, исключая те, у которых имеются сегменты
     * с датой прилета раньше даты вылета.
     *
     * @param flights Список перелетов для фильтрации.
     * @return Список перелетов, прошедших фильтрацию.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> flightsFilter = new ArrayList<>();
        try {
            flightsFilter = flights.stream()
                    .filter(it -> it.getSegments().stream()
                            .allMatch(seg -> seg.getArrivalDate().isAfter(seg.getDepartureDate())))
                    .collect(Collectors.toList());
        } catch (DateTimeException | NullPointerException | IndexOutOfBoundsException | IllegalArgumentException ex) {
            System.err.println("Ошибка при фильтрации полетов с датой прилета раньше даты вылета." + ex.getMessage());
        }
        return flightsFilter;
    }
}
