package com.example.oprs.controller;

import com.example.oprs.model.User;
import com.example.oprs.security.UserPrincipal;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class AccountController {
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/account")
    public String account(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        User user=userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user.getFirstName());
        model.addAttribute("message", "you successfully login");
        return "testAccount";
    }
}
