package com.example.oprs.controller;

import com.example.oprs.dto.ApplicationInfoDto;
import com.example.oprs.dto.HistoryDto;
import com.example.oprs.dto.UserDto;
import com.example.oprs.service.ApplicationService;
import com.example.oprs.service.HistoryService;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final HistoryService historyService;
    private final UserService userService;
    private final ApplicationService applicationService;

    public AccountController(HistoryService historyService, UserService userService, ApplicationService applicationService) {
        this.historyService = historyService;
        this.userService = userService;

        this.applicationService = applicationService;
    }

    @GetMapping("/account")
    public Object account(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        UserDto user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("message", "you successfully login");


        String path = "C:\\Users\\sahaks\\Desktop\\oprs\\src\\main\\resources\\static\\xml\\xmlfile.xml";
        model.addAttribute("file", new File(path));


        return "account/account";
    }

    @GetMapping("/purpose")
    public String purpose() {
        return "account/purpose";
    }

    @PostMapping("/CreateNewRequest")
    public String choose(String purpose) {
        return "account/purpose";
    }

    @GetMapping("/track/{token}")
    public String track(@PathVariable("token") String token, HttpServletRequest req, Model model) {
        Principal principal = req.getUserPrincipal();
        UserDto user = userService.getUserByEmail(principal.getName());
        List<ApplicationInfoDto> requests = applicationService.getRequestByToken(token);

        if (!requests.isEmpty() && user.getId().equals(requests.get(0).getUserId())) {
            model.addAttribute("requests", requests);
        } else {
            model.addAttribute("message", " invalid action something is not correct ");
        }
        return "account/requestTrack";
    }

    @PostMapping("/history")
    public String history(HttpServletRequest req, Model model, Long applicationId) {
        Principal principal = req.getUserPrincipal();
        UserDto user = userService.getUserByEmail(principal.getName());
        List<HistoryDto> histories = historyService.getHistoryByRequestInfoId(applicationId);
        if (!histories.isEmpty() && user.getId().equals(histories.get(0).getUserId())) {
            model.addAttribute("histories", histories);
        } else {
            model.addAttribute("message", " invalid action something is not correct ");
        }
        return "account/history";
    }


}
