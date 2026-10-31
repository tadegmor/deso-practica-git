package service;

import domain.Reservation;
import domain.TouristService;
import domain.User;
import dto.reservation.CreateReservationRequest;
import dto.reservation.ReservationResponse;
import dto.reservation.ValidationResultDTO;
import dto.user.UserSummaryDTO;
import exception.DataAccessException;
import exception.DuplicateReservationException;
import exception.FlightNotFoundException;
import exception.InvalidDateRangeException;
import exception.UserNotFoundException;
import repository.dao.FlightDao;
import repository.dao.ReservationDao;
import repository.dao.ReservationExportDao;
import repository.dao.UserDao;
import util.Pair;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ReservationService {
  private final ReservationDao reservationDao;
  private final UserDao userDao;
  private final FlightDao flightDao;

  public ReservationService(ReservationDao reservationDao, UserDao userDao, FlightDao flightDao) {
    this.reservationDao = reservationDao;
    this.userDao = userDao;
    this.flightDao = flightDao;
  }

  public ReservationResponse createReservation(CreateReservationRequest request)
      throws DataAccessException, UserNotFoundException, FlightNotFoundException {
    // 1. Resolver entidades de dominio desde los datos del DTO
    User user =
        userDao
            .findByUsername(request.username())
            .orElseThrow(() -> new UserNotFoundException(request.username()));
    TouristService service =
        flightDao
            .findByNumber(request.flightNumber())
            .orElseThrow(() -> new FlightNotFoundException(request.flightNumber()));

    // 2. Construir la reserva (lógica de dominio)
    Reservation reservation =
        new Reservation.Builder()
            .id((long) (Math.random() * 1000))
            .owner(user)
            .service(service)
            .date(request.date())
            .build();

    reservationDao.save(reservation);

    // 3. Mapear a DTO de respuesta
    return new ReservationResponse(
        reservation.getId(),
        user.getUsername(),
        service.toString(),
        reservation.calculatePrice(),
        request.date());
  }

  // Ejercicio 1
  public List<Reservation> getReservationsByUser(
      List<Reservation> reservations, String username, UserDao userDao)
      throws UserNotFoundException, DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 1: implement getReservationsByUser");
  }

  // Ejercicio 3
  public List<Reservation> getTopNMostExpensive(
      ReservationDao reservationDao, Instant from, Instant to, int n)
      throws InvalidDateRangeException, DataAccessException {
        if(n <= 0){
          return List.of();
        }
        if(from == null || to == null || from.isAfter(to)){
          throw new InvalidDateRangeException("Rango de fechas inválido: 'from' debe ser anterior a 'to'");
        }

        return reservationDao.findByDateRange(from, to).stream()
                .sorted(Comparator.comparingDouble(Reservation::calculatePrice).reversed())
                .limit(n)
                .collect(Collectors.toList());
        
  }

  // Ejercicio 5
  public Map<String, List<Reservation>> groupReservationsByCountry(List<Reservation> reservations) {
    throw new UnsupportedOperationException("Ejercicio 5: implement groupReservationsByCountry");
  }

  // Ejercicio 7
  public Reservation createReservation(
      String username,
      String flightNumber,
      Instant date,
      UserDao userDao,
      FlightDao flightDao,
      ReservationDao reservationDao)
      throws UserNotFoundException,
          FlightNotFoundException,
          InvalidDateRangeException,
          DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 7: implement createReservation");
  }

  // Ejercicio 8
  public Reservation addReservationIfNotDuplicate(
      List<Reservation> existingReservations,
      Reservation newReservation,
      ReservationDao reservationDao)
      throws DuplicateReservationException, DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 8: implement addReservationIfNotDuplicate");
  }

  // Ejercicio 10
  public void exportReservationsToCsv(
      List<Reservation> reservations, ReservationExportDao exportDao) throws DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 10: implement exportReservationsToCsv");
  }

  // Ejercicio 11
  public List<UserSummaryDTO> getUserReservationSummaries(
      List<Reservation> reservations, UserDao userDao)
      throws UserNotFoundException, DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 11: implement getUserReservationSummaries");
  }

  // Ejercicio 12
  public ValidationResultDTO validateReservationCandidates(
      List<Pair<String, String>> candidates, UserDao userDao, FlightDao flightDao)
      throws DataAccessException {
    throw new UnsupportedOperationException(
        "Ejercicio 12: implement validateReservationCandidates");
  }
}
