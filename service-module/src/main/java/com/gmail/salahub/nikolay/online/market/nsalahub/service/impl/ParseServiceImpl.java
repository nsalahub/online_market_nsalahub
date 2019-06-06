package com.gmail.salahub.nikolay.online.market.nsalahub.service.impl;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.ParserService;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.handler.SAXParserHandler;
import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemUploadDTO;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ParseServiceImpl implements ParserService {

    @Override
    public List<ItemUploadDTO> parseInputStream(InputStream xmlInputStream)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        SAXParserHandler handler = new SAXParserHandler();
        parser.parse(xmlInputStream, handler);
        return handler.getItemDTOS();
    }
}
