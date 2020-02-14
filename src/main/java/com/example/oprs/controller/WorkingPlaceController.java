package com.example.oprs.controller;

import com.example.oprs.pojo.*;
import com.example.oprs.service.RequestService;
import com.example.oprs.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/workPlace")
public class WorkingPlaceController {
    private final RequestService requestService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public WorkingPlaceController(RequestService requestService, UserService userService, PasswordEncoder passwordEncoder) {
        this.requestService = requestService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/work")
    public String log() {
        return "officer/work";
    }

    @GetMapping("/getRequests")
    public String getAll(Model model) {
        return "officer/viewForOfficers";
    }

    @PostMapping("/getRequests")
    public String Search(Model model, Search search) {
        List<RequestInfo> requests = requestService.search(search);
        model.addAttribute("requests", requests);
        return "officer/viewForOfficers";
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        return "officer/changePassword";
    }

    @PostMapping("/changePassword")
    public Object change(HttpServletRequest request, ChangePassword changePassword, Model model) {
        if (!changePassword.getNewPassword().equals(changePassword.getRepeatPassword())) {
            model.addAttribute("message", "Repeated Password in not equals to New Password");
            return "officer/changePassword";
        } else {
            Principal principal = request.getUserPrincipal();
            User user = userService.getUserByEmail(principal.getName());

            if (passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(changePassword.getNewPassword());
                userService.updatePassword(encodedNewPassword, principal.getName());
                return new RedirectView("/account");
            } else {
                model.addAttribute("message", "you put wrong old password");
                return "officer/changePassword";
            }
        }
    }

    @PostMapping("/RequestsDetail")
    public String RequestsDetail(Model model, Long id) {
        List<RequestInfo> requests = requestService.getRequestById(id);
        model.addAttribute("requests", requests);
        return "officer/RequestsDetail";
    }

    @PostMapping("/editRequest")
    public String editRequest(Model model, RequestInfo requestInfo) {

        //      ??????????????????

        return "officer/viewForOfficers";
    }

    @PostMapping("/editStatus")
    public String editStatus(Model model, Status status) {


        //      ??????????????????


        return "officer/viewForOfficers";
    }
}
