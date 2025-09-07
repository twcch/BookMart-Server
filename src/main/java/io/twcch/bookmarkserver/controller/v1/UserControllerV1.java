package io.twcch.bookmarkserver.controller.v1;

import io.twcch.bookmarkserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserControllerV1 {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

}
