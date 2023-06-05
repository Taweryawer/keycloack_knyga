package service.impl;

import dao.RoleDao;
import dao.UserDao;
import domain.Role;
import domain.User;
import dto.UserDto;
import exception.NoDefaultRoleException;
import exception.NullUserException;
import exception.NoUserFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;
import util.UserDtoMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private RoleDao roleDao;
    private UserDao userDao;
    private UserDtoMapper userMapper;
    private Logger log = LogManager.getLogger(UserServiceImpl.class);
    private static final String DEFAULT_ROLE_NAME = "User";

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao,
        UserDtoMapper userMapper) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public boolean createUser(UserDto userDto) {
        User user = userMapper.dtoToUser(userDto);
        if (user == null) {
            throw new NullUserException("User can't be null");
        }
        Long roleId = Long.valueOf(userDto.getRoleId());
        Optional<Role> role = roleDao.findById(roleId);
        if (role.isPresent()) {
            log.info("Received user {} with role {}", user, role);
            user.setRole(role.get());
            log.info("Saving user {}", user);
            userDao.create(user);
            log.info("User {} successfully saved", user);
            return true;
        } else {
            log.warn("Unknown role, user will not be saved");
            return false;
        }
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public Optional<UserDto> getUserByLogin(String login) {
        Optional<User> user = userDao.findByLogin(login);
        return Optional.ofNullable(userMapper.userToDto(user.orElseThrow(
            () -> new NoUserFoundException(
                "No user with login " + login + " found"))));
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        User user = userMapper.dtoToUser(userDto);
        if (user == null) {
            throw new NullUserException("User can't be null");
        }
        Long roleId = Long.valueOf(userDto.getRoleId());
        Optional<Role> role = roleDao.findById(roleId);
        log.info("Received user {} from form, with role {}", user, role);

        if (role.isPresent()) {
            user.setRole(role.get());
            user.setId(getIdForLogin(user.getLogin()));
            userDao.update(user);
            log.info("User {} successfully updated", user.getLogin());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeUserByLogin(String login) {
        log.info("Searching for user {}", login);
        Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent()) {
            log.info("User found, removing");
            userDao.remove(user.get());
            log.info("User successfully removed");
            return true;
        } else {
            log.warn("User {} doesn't exist, failed to remove", login);
            return false;
        }
    }

    @Override
    public boolean registerUser(UserDto userDto) {
        Optional<Role> defaultRole = roleDao.findByName(DEFAULT_ROLE_NAME);
        if (defaultRole.isPresent()) {
            userDto.setRoleId(defaultRole.get().getId().toString());
            return createUser(userDto);
        } else {
            throw new NoDefaultRoleException(
                "No role with name " + DEFAULT_ROLE_NAME + " found");
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(userMapper.userToDto(user));
        }
        return dtos;
    }

    private Long getIdForLogin(String login) {
        Optional<User> userDto = userDao.findByLogin(login);
        if (userDto.isPresent()) {
            return userDto.get().getId();
        } else {
            throw new NullUserException("No user with login " + login + " found");
        }
    }
}
