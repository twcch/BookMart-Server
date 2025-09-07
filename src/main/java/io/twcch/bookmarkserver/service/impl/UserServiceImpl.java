package io.twcch.bookmarkserver.service.impl;

import io.twcch.bookmarkserver.dao.UserDao;
import io.twcch.bookmarkserver.dto.UserRegisterRequest;
import io.twcch.bookmarkserver.model.User;
import io.twcch.bookmarkserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        return userDao.createUser(userRegisterRequest);

    }

    @Override
    public User getUserById(Integer userId) {

        return userDao.getUserById(userId);

    }

}
