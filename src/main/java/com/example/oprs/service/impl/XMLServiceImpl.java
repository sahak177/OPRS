package com.example.oprs.service.impl;

import com.example.oprs.pojo.ApplicationInfo;
import com.example.oprs.service.XMLService;
import com.example.oprs.util.SAXParserExample;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.annotation.PreDestroy;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@SessionScope
public class XMLServiceImpl implements XMLService {
    private static int iterationSessionId;
    private int sessionId;
    private File file;
    public static final String downloadXmlFilePath = "\\xml\\";

    public XMLServiceImpl() {
        iterationSessionId++;
        this.sessionId = iterationSessionId;
    }

    @Override
    public ApplicationInfo readObjectFromXML(MultipartFile multipartFile) throws IOException, ParserConfigurationException, SAXException {
        File file = convertMultiPartToFile(multipartFile);
        ApplicationInfo applicationInfo = SAXParserExample.readeXmlDocument(file);
        file.delete();
        return applicationInfo;
    }

    @Override
    public String WriteObjectToXML(ApplicationInfo applicationInfo) {

        this.file = SAXParserExample.createAndWriteXmlDocument(applicationInfo, sessionId);

        return downloadXmlFilePath + file.getName();
    }

    private File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {

        String rootPath = "src/main/resources/static/xml.Input/";
        File file = new File(rootPath + "xml" + sessionId + ".xml");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        return file;
    }

    @PreDestroy
    private void delete() {
        this.file.delete();
    }
}
