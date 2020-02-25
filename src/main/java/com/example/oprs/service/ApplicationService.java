package com.example.oprs.service;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.pojo.Search;
import com.example.oprs.pojo.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ApplicationService {

    boolean doRequest(ApplicationInfo applicationInfo, String email);

    List<ApplicationInfo> getAll(String status);

    void validateRequestInfo(ApplicationInfo applicationInfo, MultipartFile multiPhoto) throws InValidInputException;

    List<ApplicationInfo> search(Search search);

    List<ApplicationInfo> getRequestByToken(String token);

    List<ApplicationInfo> getRequestById(Long id);

    void updateStatus(Status status, Long id);

    void updateRequest(ApplicationInfo applicationInfo);
}
