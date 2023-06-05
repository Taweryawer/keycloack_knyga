package service.impl;

import dao.UserDao;
import domain.User;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s)
        throws UsernameNotFoundException {
        Optional<User> foundUser = userDao.findByLogin(s);
        if (foundUser.isPresent() && foundUser.get().getRole() != null) {
            return foundUser.get();
        } else {
            throw new UsernameNotFoundException("User doesn't exist or has no role");
        }
    }
}
