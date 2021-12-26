package opt.h1b.OPT_H1B.controller.api;

import opt.h1b.OPT_H1B.domain.User;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signUp")
    public String saveUser(@RequestBody User userClass){
        User savedUser = userService.save(userClass);
        if(savedUser.getId() != 0){
            return "Success";

        } else {
            return null;
        }
    }
    @GetMapping(path = "/getAll", produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

}
