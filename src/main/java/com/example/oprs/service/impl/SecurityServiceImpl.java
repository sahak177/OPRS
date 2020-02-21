package com.example.oprs.service.impl;

import com.example.oprs.exception.InValidInputException;
import com.example.oprs.service.SecurityService;
import com.example.oprs.util.TextToGraphicConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@SessionScope
public class SecurityServiceImpl implements SecurityService {
    private String securityCode;

    @Override
    public String getNewSecurityImgURL() throws IOException {
        String imgName = "photo.png";
        createSecurityCode();
        String uploadDir = "target\\classes\\static\\img\\";
        BufferedImage image = new TextToGraphicConverter().convertTextToGraphic(securityCode, new Font("Arial", Font.PLAIN, 18));
        ImageIO.write(image, "png", new File(uploadDir + imgName));
        return "\\img\\" + imgName;
    }

    @Override
    public void checkSecurityCode(String securityCodeInput) throws InValidInputException {
        if (!securityCodeInput.equals(securityCode)) {
            throw new InValidInputException("wrong security code");
        }
    }

    private void createSecurityCode() {
        String st = UUID.randomUUID().toString();
        this.securityCode = st.substring(0, 6);
    }
}
