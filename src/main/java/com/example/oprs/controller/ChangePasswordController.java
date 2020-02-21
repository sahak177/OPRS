package com.example.oprs.controller;

import com.example.oprs.pojo.ChangePassword;
import com.example.oprs.pojo.User;
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

@Controller
@RequestMapping("/change")
public class ChangePasswordController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public ChangePasswordController( UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model) {
        return "change.password/changePassword";
    }

    @PostMapping("/changePassword")
    public Object change(HttpServletRequest request, ChangePassword changePassword, Model model) {

        if (!changePassword.getNewPassword().equals(changePassword.getRepeatPassword())) {
            model.addAttribute("message", "Repeated Password in not equals to New Password");
            return "change.password/changePassword";
        } else {
            Principal principal = request.getUserPrincipal();
            User user = userService.getUserByEmail(principal.getName());

            if (passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(changePassword.getNewPassword());
                userService.updatePassword(encodedNewPassword, principal.getName());
                return new RedirectView("/account");
            } else {
                model.addAttribute("message", "you put wrong old password");
                return "change.password/changePassword";
            }
        }
    }
}
