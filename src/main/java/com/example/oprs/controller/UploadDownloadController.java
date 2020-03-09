package com.example.oprs.controller;

import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.pojo.User;
import com.example.oprs.service.ApplicationService;
import com.example.oprs.service.UserService;
import com.example.oprs.service.XMLService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/xml")
public class UploadDownloadController {
    private final ApplicationService applicationService;
    private final XMLService xmlService;
    private final UserService userService;

    public UploadDownloadController(ApplicationService applicationService, XMLService xmlService, UserService userService) {
        this.applicationService = applicationService;
        this.xmlService = xmlService;
        this.userService = userService;
    }

    @PostMapping("/download")
    public String downloadFile(HttpServletRequest request, Model model, Long id) {
        Principal principal = request.getUserPrincipal();
        User user = userService.getUserByEmail(principal.getName());
        List<ApplicationInfo> app = applicationService.getRequestById(id);
        if (!app.isEmpty() && user.getId().equals(app.get(0).getUserId())) {
            String path = xmlService.WriteObjectToXML(app.get(0));
            model.addAttribute("file", path);
            return "download";
        } else {
            model.addAttribute("message", " invalid action something is not correct ");
            return "basic/message";
        }
    }


    @GetMapping("/upload")
    public String uploadFile() {
        return "upload";
    }


    @PostMapping("/upload")
    public String uploadFilePost(MultipartFile multi, HttpServletRequest request, Model model) throws IOException, SAXException, ParserConfigurationException {
        String photoName = "Demo";
        if (!multi.isEmpty()) {
            Principal principal = request.getUserPrincipal();
            ApplicationInfo app = xmlService.readObjectFromXML(multi);
            app.setPhotoUrl(photoName);
            applicationService.doRequest(app, principal.getName());
            model.addAttribute("message", app.getFirstName() + "   your request added to validation");
        } else {
            model.addAttribute("message", "your input is not correct");
        }
        return "basic/message";
    }
}
