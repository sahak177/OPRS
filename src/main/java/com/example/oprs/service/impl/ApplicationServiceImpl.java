package com.example.oprs.service.impl;

import com.example.oprs.dao.ApplicationInfo;
import com.example.oprs.dao.Search;
import com.example.oprs.dto.ApplicationInfoDto;
import com.example.oprs.dto.SearchDto;
import com.example.oprs.enums.Status;
import com.example.oprs.mappers.map.dto.dao.ApplicationMapper;
import com.example.oprs.mappers.map.dto.dao.SearchMapper;
import com.example.oprs.repository.RequestRepository;
import com.example.oprs.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final RequestRepository requestRepository;
    private final SearchMapper searchMapper;
    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(RequestRepository requestRepository, SearchMapper searchMapper, ApplicationMapper applicationMapper) {
        this.requestRepository = requestRepository;
        this.searchMapper = searchMapper;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public boolean doRequest(ApplicationInfoDto applicationInfoDto, String email) {
        ApplicationInfo applicationInfo = applicationMapper.map(applicationInfoDto, ApplicationInfo.class);
        applicationInfo.setToken(UUID.randomUUID().toString());
        return requestRepository.addRequest(applicationInfo, email);
    }

    @Override
    public List<ApplicationInfoDto> getAll(String status) {
        List<ApplicationInfo> app = requestRepository.getRequestByStatus(status);
        return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
    }


    @Override
    public List<ApplicationInfoDto> search(SearchDto searchDto) {

        Search search = searchMapper.map(searchDto, Search.class);
        if (search.getSocialSecurityNumber() != null) {
            List<ApplicationInfo> app = requestRepository.getRequestBySSN(search.getSocialSecurityNumber());
            return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
        } else if (search.getToken() != null && search.getToken().length() > 0) {
            List<ApplicationInfo> app = requestRepository.getRequestByToken(search.getToken());
            return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
        } else if (search.getName() != null && search.getName().length() > 0) {
            List<ApplicationInfo> app = requestRepository.getRequestByName(search.getName());
            return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
        }
        List<ApplicationInfo> app = requestRepository.getRequestByStatus(search.getStatus().name());
        return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
    }

    @Override
    public List<ApplicationInfoDto> getRequestByToken(String token) {
        List<ApplicationInfo> app = requestRepository.getRequestByToken(token);
        return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
    }

    @Override
    public List<ApplicationInfoDto> getRequestById(Long id) {
        List<ApplicationInfo> app = requestRepository.getRequestById(id);
        return applicationMapper.mapAsList(app, ApplicationInfoDto.class);
    }

    @Override
    public void updateStatus(Status status, Long id) {
        requestRepository.updateStatus(status, id);
    }

    @Override
    public void updateRequest(ApplicationInfoDto applicationInfo) {
        ApplicationInfo app = applicationMapper.map(applicationInfo, ApplicationInfo.class);
        requestRepository.updateRequest(app);
    }

}
