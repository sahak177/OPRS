package com.example.oprs.repository;

import com.example.oprs.dao.ApplicationInfo;
import com.example.oprs.enums.Status;

import java.util.List;

public interface RequestRepository {

    List<ApplicationInfo> getRequestByToken(String token);

    List<ApplicationInfo> getRequestById(Long id);

    List<ApplicationInfo> getRequestByStatus(String status);

    boolean addRequest(ApplicationInfo applicationInfo, String userEmail);

    List<ApplicationInfo> getRequestBySSN(Long socialSecurityNumber);

    List<ApplicationInfo> getRequestByName(String name);

    void updateStatus(Status status, Long id);

    void updateRequest(ApplicationInfo applicationInfo);

}
