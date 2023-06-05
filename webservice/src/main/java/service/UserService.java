package service;

import domain.Role;
import dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Saves user in the database using UserDao.
     * @param userDto Data transfer object of user to save
     * @return true if user was successfully saved, false if some error occured
     */
    boolean createUser(UserDto userDto);

    /**
     * Returns all roles currently in database.
     * @return List of all roles currently in database.
     */
    List<Role> getAllRoles();

    Optional<UserDto> getUserByLogin(String login);

    boolean updateUser(UserDto userDto);

    boolean removeUserByLogin(String login);

    boolean registerUser(UserDto userDto);

    List<UserDto> getAllUsers();
}
