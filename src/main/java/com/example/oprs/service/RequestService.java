package com.example.oprs.service;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.Search;

import java.util.List;

public interface RequestService {

    boolean doRequest(RequestInfo requestInfo, String email);

    List<RequestInfo> getAll(String status);

    void validateRequestInfo(RequestInfo requestInfo) throws InValidInputException;

    List<RequestInfo> search(Search search);

    List<RequestInfo> getRequestByToken(String token);

    List<RequestInfo> getRequestById(Long id);
}
