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
@RequestMapping("/vard")
public class VARDOfficerController {
    private final ApplicationService applicationService;


    public VARDOfficerController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }


    @GetMapping("/work")
    public String work() {
        return "vard_officer/work";
    }

    @GetMapping("/getRequests")
    public String getRequests(Model model) {
        return "vard_officer/viewForOfficers";
    }

    @PostMapping("/getRequests")
    public String Search(Model model, Search search) {
        List<ApplicationInfo> requests = applicationService.search(search);
        model.addAttribute("requests", requests);
        return "vard_officer/viewForOfficers";
    }


    @PostMapping("/requestsDetail")
    public String RequestsDetail(Model model, Long id) {
        List<ApplicationInfo> requests = applicationService.getRequestById(id);
        model.addAttribute("requests", requests);
        return "vard_officer/RequestsDetail";
    }

    @PostMapping("/updateStatus")
    public String editStatus(Status status, Long id) {
        applicationService.updateStatus(status, id);
        return "vard_officer/viewForOfficers";
    }
}
