package com.example.oprs.controller;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.User;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public Object signin(HttpServletRequest request) {

        if (request.getUserPrincipal() != null) {
            return new RedirectView("/account");
        }

        return "basic/login";
    }


    @GetMapping("/signup")
    public String signupGet(User user, Model model) {
        model.addAttribute("user", user);
        return "basic/register";
    }


    @PostMapping("/signup")
    public String signup(User user, Model model) {
        try {
            userService.validateInput(user);
            userService.add(user);
            model.addAttribute("message", user.getFirstName() + " you successfully registered");
        } catch (InValidInputException inValidInputException) {
            model.addAttribute("user", user);
            model.addAttribute("message", " something went wrong try again\n" + inValidInputException.getMessage());
            return "basic/register";
        }


        return "basic/message";
    }


    @GetMapping("/")
    public String log(Model model) {
        return "basic/index";
//        return "basic/index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}

