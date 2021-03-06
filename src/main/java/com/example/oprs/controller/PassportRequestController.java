package com.example.oprs.controller;

import com.example.oprs.pojo.Purpose;
import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.service.FileService;
import com.example.oprs.service.ApplicationService;
import com.example.oprs.util.UniqueNameGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/passport")
public class PassportRequestController {

    private final FileService fileService;
    private final ApplicationService applicationService;

    public PassportRequestController(FileService fileService, ApplicationService applicationService) {
        this.fileService = fileService;
        this.applicationService = applicationService;
    }

    @PostMapping("/step")
    public Object step(ApplicationInfo applicationInfo, String purpose, Model model) {
        if (Purpose.valueOf(purpose) == null) {
            return new RedirectView("account/purpose");
        }
        applicationInfo.setPurpose(Purpose.valueOf(purpose));
        return "passport/NewPassport";
    }


    @PostMapping("/step1")
    public Object step1(@Valid ApplicationInfo applicationInfo, Errors errors, MultipartFile multiPhoto, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("applicationInfo", applicationInfo);
            model.addAttribute("photo", multiPhoto);
            model.addAttribute("message", "something went Wrong   ");
            return "passport/NewPassport";
        } else {
            Principal principal = request.getUserPrincipal();
            String name = UniqueNameGenerator.generateName("photo");
            String photoName = fileService.uploadFile(multiPhoto, name);
            applicationInfo.setPhotoUrl(photoName);
            applicationService.doRequest(applicationInfo, principal.getName());
            return "passport/request5";

        }


    }


}