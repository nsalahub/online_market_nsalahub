package com.gmail.salahub.nikolay.online.market.nsalahub.service.handler;

import com.gmail.salahub.nikolay.online.market.nsalahub.service.model.ItemUploadDTO;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {

    private boolean bName;
    private boolean bDescription;
    private boolean bPrice;
    private boolean bUniqueNumber;

    private List<String> prices = new ArrayList<>();
    private List<String> uniqueNumbers = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> descriptions = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("name")) {
            bName = true;
        }
        if (qName.equalsIgnoreCase("description")) {
            bDescription = true;
        }
        if (qName.equalsIgnoreCase("price")) {
            bPrice = true;
        }
        if (qName.equalsIgnoreCase("uniqueNumber")) {
            bUniqueNumber = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bName) {
            names.add(new String(ch, start, length));
            bName = false;
        } else if (bDescription) {
            descriptions.add(new String(ch, start, length));
            bDescription = false;
        } else if (bPrice) {
            prices.add(new String(ch, start, length));
            bPrice = false;
        } else if (bUniqueNumber) {
            uniqueNumbers.add(new String(ch, start, length));
            bUniqueNumber = false;
        }
    }

    public List<ItemUploadDTO> getItemDTOS() {
        List<ItemUploadDTO> uploadDTOS = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            ItemUploadDTO itemUploadDTO = new ItemUploadDTO();
            itemUploadDTO.setName(names.get(i));
            itemUploadDTO.setUniqueNumber(uniqueNumbers.get(i));
            itemUploadDTO.setDescription(descriptions.get(i));
            itemUploadDTO.setPrice(prices.get(i));
            uploadDTOS.add(itemUploadDTO);
        }
        return uploadDTOS;
    }
}
