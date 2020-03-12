package com.example.oprs.service;

import com.example.oprs.dto.ApplicationInfoDto;
import com.example.oprs.dto.SearchDto;
import com.example.oprs.enums.Status;

import java.util.List;

public interface ApplicationService {


    boolean doRequest(ApplicationInfoDto applicationInfoDto, String email);

    List<ApplicationInfoDto> getAll(String status);

    List<ApplicationInfoDto> search(SearchDto searchDto);

    List<ApplicationInfoDto> getRequestByToken(String token);

    List<ApplicationInfoDto> getRequestById(Long id);

    void updateStatus(Status status, Long id);


    void updateRequest(ApplicationInfoDto applicationInfo);
}
