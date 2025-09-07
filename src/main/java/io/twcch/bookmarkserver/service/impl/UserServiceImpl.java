package io.twcch.bookmarkserver.service.impl;

import io.twcch.bookmarkserver.dao.UserDao;
import io.twcch.bookmarkserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDao;

}
