package com.example.oprs.util;

import com.example.oprs.dto.ApplicationInfoDto;
import com.example.oprs.enums.Purpose;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SAXParserExample extends DefaultHandler {

    private static ApplicationInfoDto app;
    private static String text;
    private static int t = 0;
    public static final String xmlFilePath = "target\\classes\\static\\xml\\";


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if ("application".equals(qName)) {
            app = new ApplicationInfoDto();
        }
    }

    @SneakyThrows
    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "application": {
                break;
            }
            case "firstname": {
                app.setFirstName(text);
                break;
            }
            case "lastname": {
                app.setLastName(text);
                break;
            }
            case "gender": {

                app.setGender(Boolean.parseBoolean(text));
                break;
            }
            case "email": {
                app.setEmail(text);
                break;
            }
            case "ssn": {
                app.setSocialSecurityNumber(text);
                break;
            }
            case "address": {
                app.setAddress(text);
                break;
            }
            case "purpose": {
                app.setPurpose(Purpose.valueOf(text));
                break;
            }
            case "country": {
                app.setCountryOfBirth(text);
                break;
            }
            case "phoneNumber": {
                app.setPhoneNumber(text);
                break;
            }
            case "lostPassportNumber": {
                app.setLostPassportNumber(text);
                break;
            }
            case "oldPassportNumber": {
                app.setOldPassportNumber(text);
                break;
            }
            case "postCode": {
                app.setPostCode(text);
                break;
            }
            case "givenDate": {
                if (!text.isEmpty()) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(text);
                    app.setGivenDate(date);
                }
                break;
            }
            case "dateOfBirth": {
                if (!text.isEmpty()) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(text);
                    app.setDateOfBirth(date);
                }
                break;
            }
            case "expireDate": {
                if (!text.isEmpty()) {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(text);
                    app.setExpireDate(date);
                }
                break;
            }
            case "fromWhom": {
                app.setFromWhom(text);
                break;
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text = String.copyValueOf(ch, start, length).trim();
    }

    public static File createAndWriteXmlDocument(ApplicationInfoDto applicationInfo, int sessionId) {
        File file = null;
        String fileName = xmlFilePath + "file" + sessionId + ".xml";
        try {

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();

            Element appClass = document.createElement("class");
            document.appendChild(appClass);

            Element application = document.createElement("application");

            appClass.appendChild(application);


            Element firstName = document.createElement("firstname");
            firstName.appendChild(document.createTextNode(applicationInfo.getFirstName()));
            application.appendChild(firstName);


            Element lastName = document.createElement("lastname");
            lastName.appendChild(document.createTextNode(applicationInfo.getLastName()));
            application.appendChild(lastName);


            Element email = document.createElement("email");
            email.appendChild(document.createTextNode(applicationInfo.getEmail()));
            application.appendChild(email);

            Element country = document.createElement("country");
            country.appendChild(document.createTextNode(applicationInfo.getCountryOfBirth()));
            application.appendChild(country);

            Element address = document.createElement("address");
            address.appendChild(document.createTextNode(applicationInfo.getAddress()));
            application.appendChild(address);

            Element phoneNumber = document.createElement("phoneNumber");
            phoneNumber.appendChild(document.createTextNode(applicationInfo.getPhoneNumber()));
            application.appendChild(phoneNumber);

            Element postCode = document.createElement("postCode");
            postCode.appendChild(document.createTextNode(applicationInfo.getPostCode()));
            application.appendChild(postCode);

            Element ssn = document.createElement("ssn");
            ssn.appendChild(document.createTextNode(applicationInfo.getSocialSecurityNumber()));
            application.appendChild(ssn);

            Element purpose = document.createElement("purpose");
            purpose.appendChild(document.createTextNode(applicationInfo.getPurpose().name()));
            application.appendChild(purpose);

            Element gender = document.createElement("gender");
            gender.appendChild(document.createTextNode(applicationInfo.getGender().toString()));
            application.appendChild(gender);

            Element lostPassportNumber = document.createElement("lostPassportNumber");
            if (!applicationInfo.getLostPassportNumber().isEmpty()) {
                lostPassportNumber.appendChild(document.createTextNode(applicationInfo.getLostPassportNumber()));
            } else {
                lostPassportNumber.appendChild(document.createTextNode(" "));
            }
            application.appendChild(lostPassportNumber);

            Element oldPassportNumber = document.createElement("oldPassportNumber");
            if (!applicationInfo.getOldPassportNumber().isEmpty()) {
                oldPassportNumber.appendChild(document.createTextNode(applicationInfo.getOldPassportNumber()));
            } else {
                oldPassportNumber.appendChild(document.createTextNode(" "));
            }
            application.appendChild(oldPassportNumber);

            Element fromWhom = document.createElement("fromWhom");
            if (!applicationInfo.getFromWhom().isEmpty()) {
                fromWhom.appendChild(document.createTextNode(applicationInfo.getFromWhom()));
            } else {
                fromWhom.appendChild(document.createTextNode(" "));
            }
            application.appendChild(fromWhom);

            Element expireDate = document.createElement("expireDate");
            if (applicationInfo.getExpireDate() != null) {
                expireDate.appendChild(document.createTextNode(applicationInfo.getExpireDate().toString()));
            } else {
                expireDate.appendChild(document.createTextNode(" "));
            }
            application.appendChild(expireDate);
            Element givenDate = document.createElement("givenDate");
            if (applicationInfo.getGivenDate() != null) {
                givenDate.appendChild(document.createTextNode(applicationInfo.getGivenDate().toString()));
            } else {
                givenDate.appendChild(document.createTextNode(" "));
            }
            application.appendChild(givenDate);

            Element dateOfBirth = document.createElement("dateOfBirth");
            dateOfBirth.appendChild(document.createTextNode(applicationInfo.getDateOfBirth().toString()));
            application.appendChild(dateOfBirth);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            file = new File(fileName);
            StreamResult streamResult = new StreamResult(file);

            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
        return file;
    }

    public static ApplicationInfoDto readeXmlDocument(File file) throws
            ParserConfigurationException, SAXException, IOException {
        String xsdPath = "src/main/resources/static/xsd/application.xsd";
        String xmlPath = file.getPath();
        validateXMLSchema(xsdPath, xmlPath);
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        SAXParser parser = parserFactor.newSAXParser();
        SAXParserExample handler = new SAXParserExample();
        parser.parse(file, handler);
        return app;
    }


    public static boolean validateXMLSchema(String xsdPath, String xmlPath) throws SAXException, IOException {
        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
        return true;
    }


}