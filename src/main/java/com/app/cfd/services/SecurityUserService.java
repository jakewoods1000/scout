package com.app.cfd.services;

import com.app.cfd.daos.UserDao;
import com.app.cfd.models.users.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class SecurityUserService implements UserDetailsService {

    final UserDao userDao;
    final PasswordEncoder passwordEncoder;

    public SecurityUserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userDao.findByUsername(email);
        if (u == null) {
            throw new UsernameNotFoundException(
                    MessageFormat.format("User with email {0} cannot be found.", email)
            );
        }
        return u.securityUser();
    }

}