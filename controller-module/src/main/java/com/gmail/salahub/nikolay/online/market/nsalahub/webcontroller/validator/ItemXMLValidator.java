package com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.validator;

import com.gmail.salahub.nikolay.online.market.nsalahub.webcontroller.exception.SchemaFilePathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ItemXMLValidator {

    private static final String SCHEMA_PATH_ERROR_MESSAGE = "schema file not found";
    private static final String SCHEMA_FILE_NAME = "schema.xsd";

    Logger logger = LoggerFactory.getLogger(ItemXMLValidator.class);

    public boolean validate(InputStream streamWithXML) {
        Source schemaFile;
        try {
            schemaFile = new StreamSource(new ClassPathResource(SCHEMA_FILE_NAME).getFile());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new SchemaFilePathException(SCHEMA_PATH_ERROR_MESSAGE, e);
        }
        Source xmlFile = new StreamSource(streamWithXML);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

}
