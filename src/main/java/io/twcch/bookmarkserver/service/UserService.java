package io.twcch.bookmarkserver.service;

import io.twcch.bookmarkserver.dto.UserRegisterRequest;
import io.twcch.bookmarkserver.model.User;

public interface UserService {

    public Integer register(UserRegisterRequest userRegisterRequest);

    public User getUserById(Integer userId);

}
