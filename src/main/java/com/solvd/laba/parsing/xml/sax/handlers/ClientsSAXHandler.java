package com.solvd.laba.parsing.xml.sax.handlers;

import com.solvd.laba.database.model.Clients;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.services.PersonsService;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ClientsSAXHandler extends DefaultHandler {
    
    private List<Clients> clients;
    private Clients currentClient;
    private String currentElement;
    private static final PersonsService PS = new PersonsService();

    public List<Clients> getClients() {
        return clients;
    }

    public Clients getCurrentClient() {
        return currentClient;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        if ("client".equals(currentElement)) {
            currentClient = new Clients();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty() && currentClient != null) {
            if ("personsId".equals(currentElement)) {
                currentClient.setPerson(PS.get(Integer.parseInt(value)));
            } else if ("status".equals(currentElement)) {
                currentClient.setStatus(value);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("client".equals(qName)) {
            if (clients == null) {
                clients = new ArrayList<>();
            }
            clients.add(currentClient);
            currentClient = null;
        }
    }
}