package com.example.oprs.controller;

import com.example.oprs.dto.UserDto;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/workPlace")
    public String workPlace() {
        return "admin/adminWorkPlace";
    }


    @GetMapping("/addOfficer")
    public String addOfficerGet(UserDto officer, Model model) {
        model.addAttribute("officer", officer);
        return "admin/registerOfficer";
    }

    @PostMapping("/addOfficer")
    public String addOfficer(@Valid UserDto officer, Errors errors, Model model, String role) {
        if (errors.hasErrors()) {
            model.addAttribute("officer", officer);
            model.addAttribute("message", " something went wrong try again\n");
            return "admin/registerOfficer";
        } else {
            userService.addAsAdmin(officer, role);
            model.addAttribute("message", officer.getFirstName() + " successfully registered");
        }
        return "basic/message";
    }

}
