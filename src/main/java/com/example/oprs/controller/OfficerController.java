package com.example.oprs.controller;

import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.pojo.Search;
import com.example.oprs.pojo.Status;
import com.example.oprs.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/workPlace")
public class OfficerController {
    private final ApplicationService applicationService;


    public OfficerController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/work")
    public String work() {
        return "officer/work";
    }

    @GetMapping("/getRequests")
    public String getRequests(Model model) {
        return "officer/viewForOfficers";
    }

    @PostMapping("/getRequests")
    public String Search(Model model, Search search) {
        List<ApplicationInfo> requests = applicationService.search(search);
        model.addAttribute("requests", requests);
        return "officer/viewForOfficers";
    }

    @PostMapping("/RequestsDetail")
    public String RequestsDetail(Model model, Long id) {
        List<ApplicationInfo> requests = applicationService.getRequestById(id);
        model.addAttribute("requests", requests);
        return "officer/RequestsDetail";
    }

    @PostMapping("/editRequest")
    public String editRequest(Model model, ApplicationInfo applicationInfo) {
        applicationService.updateRequest(applicationInfo);
        return "officer/viewForOfficers";
    }

    @PostMapping("/editStatus")
    public String editStatus(Model model, Status status, Long id) {
        applicationService.updateStatus(status, id);
        return "officer/viewForOfficers";
    }
}
