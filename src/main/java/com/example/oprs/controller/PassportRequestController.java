package com.example.oprs.controller;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.Purpose;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.service.FileService;
import com.example.oprs.service.RequestService;
import com.example.oprs.util.UniqueNameGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/passport")
public class PassportRequestController {

    private final FileService fileService;
    private final RequestService requestService;

    public PassportRequestController(FileService fileService, RequestService requestService) {
        this.fileService = fileService;
        this.requestService = requestService;
    }

    @PostMapping("/step")
    public String step(Model model, RequestInfo requestInfo, String purpose) {
        requestInfo.setPurpose(Purpose.valueOf(purpose));
        model.addAttribute("request", requestInfo);
        return "passport/NewPassport";
    }


    @PostMapping("/step1")
    public Object step1(HttpServletRequest request, Model model, RequestInfo requestInfo, MultipartFile multiPhoto) {

        try {
            requestService.validateRequestInfo(requestInfo);
            Principal principal = request.getUserPrincipal();
            String name = UniqueNameGenerator.generateName("photo");
            String photoName = fileService.uploadFile(multiPhoto, name);
            requestInfo.setPhoto(photoName);
            requestService.doRequest(requestInfo, principal.getName());
            return "passport/request5";
        } catch (InValidInputException e) {
            model.addAttribute("message", "something vent Wrong");
            model.addAttribute("request", requestInfo);
            return new RedirectView("/passport/step");
        }

    }


}