package opt.h1b.OPT_H1B.controller.api;

import opt.h1b.OPT_H1B.domain.User;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/signUp")
    public User saveUser(@RequestBody User userClass){
        userClass.setPassword(passwordEncoder.encode(userClass.getPassword()));
        User savedUser = userService.save(userClass);
        if(savedUser.getId() != 0){
            return savedUser;

        } else {
            return null;
        }
    }
    @GetMapping(path = "/getAll", produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping(path = "/login")
    public User loginUser(@RequestBody User user){
        String password = user.getPassword();

        User dbUser = userService.findByUserName(user.getUserName());
        System.out.println(dbUser.getPassword());
        if(dbUser != null){
            if(passwordEncoder.matches(password, dbUser.getPassword())){

                return dbUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
