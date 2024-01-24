package com.solvd.laba.parsing.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Demo {
    public static void main(String[] args) {

        try {
            File folder = new File("path_to_your_folder"); // replace with your directory
            File[] listOfFiles = folder.listFiles();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".xml")) {
                    saxParser.parse(file, userhandler);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class UserHandler extends DefaultHandler {

    @Override
    public void startElement(String uri,
                             String localName, String qName, Attributes attributes)
            throws SAXException {
        System.out.println("Start Element :" + qName);
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {
        System.out.println("End Element :" + qName);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        System.out.println("Characters: " + new String(ch, start, length));
    }
}
