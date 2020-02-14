package com.example.oprs.service.impl;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.Search;
import com.example.oprs.pojo.Status;
import com.example.oprs.repository.RequestRepository;
import com.example.oprs.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public boolean doRequest(RequestInfo requestInfo, String email) {
        requestInfo.setToken(UUID.randomUUID().toString());
        return requestRepository.addRequest(requestInfo, email);
    }

    @Override
    public List<RequestInfo> getAll(String status) {

        return requestRepository.getRequestByStatus(status);
    }

    @Override
    public void validateRequestInfo(RequestInfo requestInfo) throws InValidInputException {

    }

    @Override
    public List<RequestInfo> search(Search search) {
        if (search.getSocialSecurityNumber() != null) {
            return requestRepository.getRequestBySSN(search.getSocialSecurityNumber());
        } else if (search.getToken() != null && search.getToken().length() > 0) {
            return requestRepository.getRequestByToken(search.getToken());
        } else if (search.getName() != null && search.getName().length() > 0) {
            return requestRepository.getRequestByName(search.getName());
        }
        return requestRepository.getRequestByStatus(Status.SUBMITTED.name());
    }

    @Override
    public List<RequestInfo> getRequestByToken(String token) {
        return requestRepository.getRequestByToken(token);
    }

    @Override
    public List<RequestInfo> getRequestById(Long id) {
        return requestRepository.getRequestById(id);
    }
}
