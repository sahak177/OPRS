package com.example.oprs.controller;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.User;
import com.example.oprs.service.SecurityService;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class AuthController {

    private final UserService userService;
    private final SecurityService securityService;

    public AuthController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
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
        String imgURL = null;
        try {
            imgURL = securityService.getNewSecurityImgURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("secureImg", imgURL);
        model.addAttribute("user", user);
        return "basic/register";
    }


    @PostMapping("/signup")
    public String signup(User user, Model model, String securityCode) {
        try {
            securityService.checkSecurityCode(securityCode);
            userService.validateInput(user);
            userService.add(user);
            model.addAttribute("message", user.getFirstName() + " you successfully registered");
        } catch (InValidInputException inValidInputException) {
            String imgURL = null;
            try {
                imgURL = securityService.getNewSecurityImgURL();
            } catch (IOException e) {
                e.printStackTrace();
            }
            model.addAttribute("secureImg", imgURL);
            model.addAttribute("user", user);
            model.addAttribute("message", " something went wrong try again\n" + inValidInputException.getMessage());
            return "basic/register";
        }


        return "basic/message";
    }


    @GetMapping("/")
    public String log(Model model) {
        return "basic/index";

    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}

