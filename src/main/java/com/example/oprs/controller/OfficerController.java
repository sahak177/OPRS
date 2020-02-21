package com.example.oprs.controller;

import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.Search;
import com.example.oprs.pojo.Status;
import com.example.oprs.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/workPlace")
public class OfficerController {
    private final RequestService requestService;


    public OfficerController(RequestService requestService) {
        this.requestService = requestService;
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
        List<RequestInfo> requests = requestService.search(search);
        model.addAttribute("requests", requests);
        return "officer/viewForOfficers";
    }

    @PostMapping("/RequestsDetail")
    public String RequestsDetail(Model model, Long id) {
        List<RequestInfo> requests = requestService.getRequestById(id);
        model.addAttribute("requests", requests);
        return "officer/RequestsDetail";
    }

    @PostMapping("/editRequest")
    public String editRequest(Model model, RequestInfo requestInfo) {
        requestService.updateRequest(requestInfo);
        return "officer/viewForOfficers";
    }

    @PostMapping("/editStatus")
    public String editStatus(Model model, Status status, Long id) {
        requestService.updateStatus(status, id);
        return "officer/viewForOfficers";
    }
}
