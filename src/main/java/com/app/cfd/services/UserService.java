package com.app.cfd.services;

import com.app.cfd.daos.UserDao;
import com.app.cfd.models.users.User;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findOrCreateByUserId(String id, String email, boolean emailVerified){
        User user = userDao.findById(id);
        if (user == null) {
            userDao.insert(id, email, emailVerified);
            user = userDao.findById(id);
        } else if (!Objects.equals(user.getEmail(), email)) {
//            if (userDao.findByEmail(email) != null){
//                throw error about existing email can not be used
//            }
            userDao.updateUser(id, email, emailVerified);
        }

        return user;
    }

    public void deactivateUser(UUID id){
//        Should we add deactivated fields on workouts, sets, etc with this user
//        or should we just delete them when the user deactivates?
        userDao.deactivateUser(id);
    }
}
