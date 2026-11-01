package service;

import domain.Flight;
import domain.TouristService;
import exception.FlightNotFoundException;
import exception.ServiceNotFoundException;
import repository.dao.FlightDao;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TouristServiceService {

  // Ejercicio 2
  public double getAveragePriceByCompany(List<TouristService> services, String company)
      throws ServiceNotFoundException {
    throw new UnsupportedOperationException("Ejercicio 2: implement getAveragePriceByCompany");
  }

  // Ejercicio 4
    public List<Flight> getFlightsSortedByPriceThenAirline(
            List<String> flightNumbers, FlightDao flightDao) throws FlightNotFoundException {
        
        List<Flight> flights = new ArrayList<>();
        
        for (String number : flightNumbers) {
            Flight flight = flightDao.findByNumber(number)
                    .orElseThrow(() -> new FlightNotFoundException("Vuelo no encontrado: " + number));
            flights.add(flight);
        }
        
        flights.sort(Comparator.comparing(Flight::calculatePrice)
                               .thenComparing(Flight::getAirline));
                               
        return flights;
    }
  
}