package com.gridnine.testing.modules.filters;

import com.gridnine.testing.modules.flights.Flight;

import java.util.List;
/**
 * Интерфейс, представляющий фильтр для списка перелетов.
 * Реализации этого интерфейса выполняют фильтрацию списка перелетов на основе определенных правил.
 */
public interface FlightFilter {
    /**
     * Фильтрует список перелетов в соответствии с определенными правилами.
     *
     * @param flights Список перелетов, который требуется отфильтровать.
     * @return Список отфильтрованных перелетов в соответствии с правилами.
     */
    List<Flight> filter(List<Flight> flights);
}
