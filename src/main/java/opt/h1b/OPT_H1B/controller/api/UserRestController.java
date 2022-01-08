package opt.h1b.OPT_H1B.controller.api;

import opt.h1b.OPT_H1B.domain.User;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    public UserRestController(UserService userService, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }


    @PostMapping("/signUp")
    public User saveUser(@RequestBody User userClass){
        userClass.setPassword(passwordEncoder.encode(userClass.getPassword()));
        User savedUser = userService.save(userClass);
        if(savedUser.getId() != 0){
            SimpleMailMessage smp = new SimpleMailMessage();
            smp.setFrom("danny.karki12345@gmail.com");
            smp.setTo(savedUser.getEmail());
            smp.setText("Thanks for Signing upto our website");
            javaMailSender.send(smp);
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
