package com.example.oprs.service;

import com.example.oprs.dto.ApplicationInfoDto;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface XMLService {

    ApplicationInfoDto readObjectFromXML(MultipartFile file) throws IOException, ParserConfigurationException, SAXException;

    String WriteObjectToXML(ApplicationInfoDto applicationInfo);
}
