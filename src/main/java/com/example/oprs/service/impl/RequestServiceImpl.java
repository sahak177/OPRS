package com.example.oprs.service.impl;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.pojo.RequestInfo;
import com.example.oprs.pojo.Search;
import com.example.oprs.pojo.Status;
import com.example.oprs.repository.RequestRepository;
import com.example.oprs.service.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void validateRequestInfo(RequestInfo req, MultipartFile multiPhoto) throws InValidInputException {

        if (req.getAddress() == null) {
            throw new InValidInputException("invalid address");
        }
        if (req.getGender() == null) {
            throw new InValidInputException("invalid input gender");
        }
        String regexName = "[A-Z][a-z]*";
        Pattern patternName = Pattern.compile(regexName);
        Matcher matcher = patternName.matcher(req.getCountryOfBirth());
        if (!matcher.matches()) {

        }
        if (req.getDateOfBirth() == null) {
            throw new InValidInputException("invalid date of birth");
        }

        Matcher matcherFName = patternName.matcher(req.getFirstName());
        if (!matcherFName.matches()) {
            throw new InValidInputException("invalid first name");
        }
        Matcher matcherLName = patternName.matcher(req.getLastName());
        if (!matcherFName.matches()) {
            throw new InValidInputException("invalid last name");
        }

        Pattern patternNumber = Pattern.compile("\\d{9}");
        Matcher matcherNumber = patternNumber.matcher(req.getSocialSecurityNumber());
        if (!matcherNumber.matches()) {
            throw new InValidInputException("invalid Social Security Number");
        }
        if (req.getPurpose().name().equals("LOST_OR_DAMAGED")) {
            String regexPassportNumber = "[A-Z,a-z]{2}\\d{7}";
            Pattern patternPassportNumber = Pattern.compile(regexPassportNumber);
            Matcher matcherPassportNumber = patternPassportNumber.matcher(req.getLostPassportNumber());
            if (!matcherPassportNumber.matches()) {
                throw new InValidInputException("invalid lost passport number");

            }
        }

        if (req.getPurpose().name().equals("EXPIRED")) {

            String regexPassportNumber = "[A-Z,a-z]{2}\\d{7}";
            Pattern patternPassportNumber = Pattern.compile(regexPassportNumber);
            Matcher matcherPassportNumber = patternPassportNumber.matcher(req.getOldPassportNumber());
            if (!matcherPassportNumber.matches()) {
                throw new InValidInputException("invalid passport number");

            }

            Pattern patternFromWhom = Pattern.compile("\\d{3}");
            Matcher matcherFromWhom = patternFromWhom.matcher(req.getFromWhom());
            if (!matcherFromWhom.matches()) {
                throw new InValidInputException("invalid from whom");
            }

            if (req.getExpireDate() == null) {
                throw new InValidInputException("invalid expire date");
            }

            if (req.getGivenDate() == null) {
                throw new InValidInputException("invalid given date");
            }
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern patternEmail = Pattern.compile(regex);
        Matcher matcherEmail = patternEmail.matcher(req.getEmail());
        if (!matcherEmail.matches()) {
            throw new InValidInputException("invalid email");
        }
        Pattern patternPhoneNumber = Pattern.compile("\\d{9}");
        Matcher matcherPhoneNumber = patternPhoneNumber.matcher(req.getPhoneNumber());

        if (!matcherPhoneNumber.matches()) {
            throw new InValidInputException("invalid phone Number");
        }
        Pattern patternPostCode = Pattern.compile("\\d{4}");
        Matcher matcherPostCode = patternPostCode.matcher(req.getPostCode());

        if (!matcherPostCode.matches()) {
            throw new InValidInputException("invalid Post Code");
        }

        if (multiPhoto.isEmpty()) {
            throw new InValidInputException("invalid input photo is not exist");
        }
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
        return requestRepository.getRequestByStatus(search.getStatus().name());
    }

    @Override
    public List<RequestInfo> getRequestByToken(String token) {
        return requestRepository.getRequestByToken(token);
    }

    @Override
    public List<RequestInfo> getRequestById(Long id) {
        return requestRepository.getRequestById(id);
    }

    @Override
    public void updateStatus(Status status, Long id) {
        requestRepository.updateStatus(status,id);
    }

    @Override
    public void updateRequest(RequestInfo requestInfo) {
        requestRepository.updateRequest(requestInfo);
    }

}
