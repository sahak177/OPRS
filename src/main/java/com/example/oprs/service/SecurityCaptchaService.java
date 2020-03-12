package com.example.oprs.service;


import com.example.oprs.exception.InValidInputException;

import java.io.IOException;

public interface SecurityCaptchaService {

    String getNewSecurityImgURL() throws IOException;

    void checkSecurityCode(String securityCode) throws InValidInputException;
}
