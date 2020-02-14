package com.example.oprs.controller;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.Officer;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String addOfficerGet(Officer officer, Model model) {
        model.addAttribute("officer", officer);
        return "admin/registerOfficer";
    }

    @PostMapping("/addOfficer")
    public String addOfficer(Officer officer, Model model) {
        try {
            officer.validateInput();
            userService.addAsAdmin(officer);
            model.addAttribute("message", officer.getFirstName() + " successfully registered");
        } catch (InValidInputException inValidInputException) {
            model.addAttribute("officer", officer);
            model.addAttribute("message", " something went wrong try again\n" + inValidInputException.getMessage());
            return "admin/registerOfficer";
        }
        return "basic/message";
    }

}
