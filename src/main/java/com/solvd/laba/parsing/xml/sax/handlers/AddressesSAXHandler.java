package com.solvd.laba.parsing.xml.sax.handlers;

import com.solvd.laba.database.model.Addresses;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AddressesSAXHandler extends DefaultHandler {

    private List<Addresses> addresses;
    private Addresses currentAddress;
    private String currentElement;

    public List<Addresses> getAddresses() {
        return addresses;
    }

    public Addresses getCurrentAddress() {
        return currentAddress;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        if ("address".equals(currentElement)) {
            currentAddress = new Addresses();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty() && currentAddress != null) {
            if ("country".equals(currentElement)) {
                currentAddress.setCountry(value);
            } else if ("city".equals(currentElement)) {
                currentAddress.setCity(value);
            } else if ("postalCode".equals(currentElement)) {
                currentAddress.setPostalCode(value);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("address".equals(qName)) {
            if (addresses == null) {
                addresses = new ArrayList<>();
            }
            addresses.add(currentAddress);
            currentAddress = null;
        }
    }
}