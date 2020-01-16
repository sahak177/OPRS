package com.example.oprs.controller;

import com.example.oprs.model.User;
import com.example.oprs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/signup")
    public String signup(){
        return "register";
    }


    @PostMapping("/auth/signup")
    public String signup(User user,Model model) {
        if(user!=null){
            userService.init(user);
            model.addAttribute("message", user.getFirstName()+" you successfully registered");
        }else {
            model.addAttribute("message",   " something went wrong ");
        }return "message";
    }

    @GetMapping("/log")
    public String log() {
        return "index";
    }


}

