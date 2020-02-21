package com.example.oprs.controller;

import com.example.oprs.pojo.History;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.User;
import com.example.oprs.service.HistoryService;
import com.example.oprs.service.RequestService;
import com.example.oprs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class AccountController {

    private final HistoryService historyService;
    private final UserService userService;
    private final RequestService requestService;

    public AccountController(HistoryService historyService, UserService userService, RequestService requestService) {
        this.historyService = historyService;
        this.userService = userService;

        this.requestService = requestService;
    }

    @GetMapping("/account")
    public Object account(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("message", "you successfully login");
        return "account/testAccount";
    }

    @GetMapping("/purpose")
    public String purpose() {
        return "account/purpose";
    }

    @PostMapping("/CreateNewRequest")
    public String choose(String purpose) {

        return "account/purpose";
    }

    @PostMapping("/track")
    public String track(HttpServletRequest req, String token, Model model) {
        Principal principal = req.getUserPrincipal();
        User user = userService.getUserByEmail(principal.getName());
        List<RequestInfo> requests = requestService.getRequestByToken(token);
        if (user.getId() == requests.get(0).getUserId()) {
            model.addAttribute("requests", requests);
        } else {
            model.addAttribute("message", " invalid action something is not correct ");
        }
        return "account/requestTrack";
    }

    @PostMapping("/history")
    public String history(HttpServletRequest req, Model model, Long requestId) {
        Principal principal = req.getUserPrincipal();
        User user = userService.getUserByEmail(principal.getName());
        List<History> histories = historyService.getHistoryByRequestInfoId(requestId);
        if (user.getId() == histories.get(0).getUserId()) {
            model.addAttribute("histories", histories);
        } else {
            model.addAttribute("message", " invalid action something is not correct ");
        }
        return "account/history";
    }


}
