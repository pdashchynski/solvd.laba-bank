package com.solvd.laba.parsing.xml.sax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParser {

    private javax.xml.parsers.SAXParser saxParser;
    private DefaultHandler handler;

    public SAXParser(DefaultHandler handler) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            saxParser = factory.newSAXParser();
            this.handler = handler;
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void parse(String xmlFilePath) {
        try {
            saxParser.parse(xmlFilePath, handler);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}