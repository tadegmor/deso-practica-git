package service;

import domain.User;
import dto.user.UserResponse;
import exception.DataAccessException;
import exception.IncompleteUserException;
import exception.UserNotFoundException;
import repository.dao.FileUserDao;
import repository.dao.UserDao;
import domain.Location;

import java.util.List;
import java.util.Optional;

public class UserService {
  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public UserResponse getByUsername(String username)
      throws UserNotFoundException, DataAccessException {
    User user =
        userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    return new UserResponse(user.getId(), user.getUsername());
  }

  // Ejercicio 6
    public User getValidatedUser(String username, UserDao userDao)
            throws UserNotFoundException, IncompleteUserException, DataAccessException {
        
        // Buscar al usuario mediante el DAO que devuelve un Optional
        Optional<User> userOptional = userDao.findByUsername(username);
        
        // Validar si existe
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        
        // Extraer el usuario real del Optional
        User user = userOptional.get();
        
        // Obtener la dirección
        Location address = user.getAddress();
        
        // Validar que address, country y city no sean nulos ni vacíos/en blanco
        if (address == null ||
            address.getCountry() == null || address.getCountry().trim().isEmpty() ||
            address.getCity() == null || address.getCity().trim().isEmpty()) {
            
            throw new IncompleteUserException(username);
        }
        
        return user;
    }

  // Ejercicio 9
  public List<User> getUsersPresentInFileAndDao(FileUserDao fileUserDao, UserDao userDao)
      throws DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 9: implement getUsersPresentInFileAndDao");
  }
}
