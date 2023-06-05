package dao;

import domain.Role;
import exception.DaoException;
import java.util.List;
import java.util.Optional;

public interface RoleDao {
    /**
     * Saves the given role in a database.
     * @param role Role object to save in a database
     * @throws exception.DaoException if SQL error occurs
     */
    void create(Role role);

    /**
     * Updates the db row that has the same id as the given Role object
     * with values of the given object.
     * @param role Role object with id and values to update
     * @throws exception.DaoException if SQL error occurs
     */
    void update(Role role);

    /**
     * Removes db row which has the same values as the given Role object
     * @param role Role object with id and values to update
     * @throws exception.DaoException if SQL error occurs
     */
    void remove(Role role);

    /**
     * Returns the role with the same name as the given string
     * @param name name to search for
     * @return Optional with Role object from db or with null if there is no such role
     * @throws DaoException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     * @throws exception.NonUniqueException if database returns 2 or more record with given name
     */
    Optional<Role> findByName(String name);

    /**
     * Returns the role with the same id as the given value
     * @param id id to search for
     * @return Optional with Role object from db or with null if there is no such role
     * @throws DaoException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     * @throws exception.NonUniqueException if database returns 2 or more record with given id
     */
    Optional<Role> findById(Long id);

    /**
     * Returns list of all roles from db.
     * @return List of all User objects from db
     * @throws exception.FindException if SQL error occurs
     * @throws exception.ConnectionException if problem while creating connection to datasource occurs
     */
    List<Role> findAll();
}
