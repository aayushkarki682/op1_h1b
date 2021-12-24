package opt.h1b.OPT_H1B.controller;

import opt.h1b.OPT_H1B.domain.UserClass;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public String saveUser(@RequestBody UserClass userClass){
        UserClass savedUser = userService.save(userClass);
        if(savedUser.getId() != 0){
            return "Success";

        } else {
            return null;
        }
    }
}
