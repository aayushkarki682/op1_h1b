package opt.h1b.OPT_H1B.controller.api;

import opt.h1b.OPT_H1B.domain.User;
import opt.h1b.OPT_H1B.exceptions.UserNotFoundException;
import opt.h1b.OPT_H1B.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.net.URI;
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
    public ResponseEntity<Object> saveUser(@RequestBody User userClass){
        userClass.setPassword(passwordEncoder.encode(userClass.getPassword()));
        User savedUser = userService.save(userClass);
        if(savedUser.getId() != 0){
            SimpleMailMessage smp = new SimpleMailMessage();
            smp.setFrom("danny.karki12345@gmail.com");
            smp.setTo(savedUser.getEmail());
            smp.setText("Thanks for Signing into our website");
            javaMailSender.send(smp);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).build();

        } else {
            return ResponseEntity.status(101).build();
        }
    }
    @GetMapping(path = "/getAll", produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping(path = "/user/{id}")
    public User getUser(@PathVariable long id){
        User retrieved = userService.findById(id);
        if(retrieved == null) {
            throw new UserNotFoundException("ID - "+ id);
        }
        return retrieved;
    }
    @PostMapping(path = "/login")
    public User loginUser(@RequestBody User user){
        String password = user.getPassword();
        User dbUser = userService.findByUserName(user.getUserName());
        if(dbUser != null){
            if(passwordEncoder.matches(password, dbUser.getPassword())){
                return dbUser;
            } else {
                throw new UserNotFoundException("Wrong password provided");
            }
        } else {
            throw new UserNotFoundException("Wrong username ");
        }
    }

}
