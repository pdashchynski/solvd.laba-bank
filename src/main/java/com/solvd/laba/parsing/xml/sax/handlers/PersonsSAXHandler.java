package com.solvd.laba.parsing.xml.sax.handlers;

import com.solvd.laba.database.model.Persons;
import com.solvd.laba.services.AddressesService;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PersonsSAXHandler extends DefaultHandler {


    private List<Persons> persons;
    private Persons currentPerson;
    private String currentElement;
    private static final AddressesService AS = new AddressesService();

    public List<Persons> getPersons() {
        return persons;
    }

    public Persons getCurrentPerson() {
        return currentPerson;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        if ("person".equals(currentElement)) {
            currentPerson = new Persons();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty() && currentPerson != null) {
            switch (currentElement) {
                case "firstName":
                    currentPerson.setFirstName(value);
                    break;
                case "middleName":
                    currentPerson.setMiddleName(value);
                    break;
                case "lastName":
                    currentPerson.setLastName(value);
                    break;
                case "age":
                    currentPerson.setAge(Short.parseShort(value));
                    break;
                case "dateOfBirth":
                    currentPerson.setDateOfBirth(Date.valueOf(value));
                    break;
                case "gender":
                    currentPerson.setGender(value);
                    break;
                case "addressesId":
                    currentPerson.setAddress(AS.get(Integer.parseInt(value)));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("person".equals(qName)) {
            if (persons == null) {
                persons = new ArrayList<>();
            }
            persons.add(currentPerson);
            currentPerson = null;
        }
    }
}