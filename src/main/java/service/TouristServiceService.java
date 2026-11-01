package service;

import domain.Flight;
import domain.TouristService;
import exception.FlightNotFoundException;
import exception.ServiceNotFoundException;
import repository.dao.FlightDao;

import java.util.List;

public class TouristServiceService {

  // Ejercicio 2
  public double getAveragePriceByCompany(List<TouristService> services, String company)
      throws ServiceNotFoundException {

    double sumaPrecios = 0;
    int cantidadServicios = 0;

    for (TouristService service : services) {
      if (service.getCompany().equalsIgnoreCase(company)) {
        sumaPrecios += service.calculatePrice();
        cantidadServicios++;
      }
    }

    if(cantidadServicios == 0){
      throw new ServiceNotFoundException(company);
    }

    return sumaPrecios / cantidadServicios;
  }

  // Ejercicio 4
  public List<Flight> getFlightsSortedByPriceThenAirline(
      List<String> flightNumbers, FlightDao flightDao) throws FlightNotFoundException {
    throw new UnsupportedOperationException(
        "Ejercicio 4: implement getFlightsSortedByPriceThenAirline");
  }
}
