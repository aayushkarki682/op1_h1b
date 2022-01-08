package opt.h1b.OPT_H1B.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    @GetMapping("/homePage")
    public String indexPage(){
        System.out.println("aayush");
        return "index";
    }
}
