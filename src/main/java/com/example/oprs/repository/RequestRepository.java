package com.example.oprs.repository;

import com.example.oprs.pojo.RequestInfo;

import java.util.List;

public interface RequestRepository {

    List<RequestInfo> getRequestByToken(String token);

    List<RequestInfo> getRequestById(Long id);

    List<RequestInfo> getRequestByStatus(String status);

    boolean addRequest(RequestInfo requestInfo,String userEmail);

    List<RequestInfo> getRequestBySSN(Long socialSecurityNumber);

    List<RequestInfo> getRequestByName(String name);
}