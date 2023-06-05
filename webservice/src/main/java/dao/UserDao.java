package dao;

import domain.User;
import exception.DaoException;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Saves the given user in a database. Sets a role specifiend in INITIAL_ROLE_NAME constant if no existing role specified.
     * @param user User object to save in a database
     * @throws exception.CreateException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     */
    void create(User user);

    /**
     * Updates the db row that has the same id as the given User object
     * with values of the given object.
     * @param user User object with id and values to update
     * @throws exception.UpdateException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     */
    void update(User user);

    /**
     * Removes db row which has the same values as the given User object
     * @param user Role object with id and values to update
     * @throws exception.RemoveException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     */
    void remove(User user);

    /**
     * Returns all users from db.
     * @return List of all User objects from db
     * @throws DaoException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     */
    List<User> findAll();

    /**
     * Returns the user with the same login as the given string
     * @param login login to search for
     * @return Optional with User object from db or with null if there is no such user
     * @throws DaoException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     * @throws exception.NonUniqueException if database returns 2 or more record with given login
     */
    Optional<User> findByLogin(String login);

    /**
     * Returns the user with the same email as the given string
     * @param email email to search for
     * @return Optional with User object from db or with null if there is no such user
     * @throws DaoException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     * @throws exception.NonUniqueException if database returns 2 or more record with given email
     */
    Optional<User> findByEmail(String email);
}
