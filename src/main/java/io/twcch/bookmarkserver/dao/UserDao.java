package io.twcch.bookmarkserver.dao;

import io.twcch.bookmarkserver.dto.UserRegisterRequest;
import io.twcch.bookmarkserver.model.User;

public interface UserDao {

    public Integer createUser(UserRegisterRequest userRegisterRequest);

    public User getUserById(Integer userId);

}
