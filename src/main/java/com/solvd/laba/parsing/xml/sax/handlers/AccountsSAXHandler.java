package com.solvd.laba.parsing.xml.sax.handlers;

import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.services.ClientsService;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountsSAXHandler extends DefaultHandler {

    private List<Accounts> accounts;
    private Accounts currentAccount;
    private String currentElement;
    private static final ClientsService CS = new ClientsService();

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public Accounts getCurrentAccount() {
        return currentAccount;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        if ("account".equals(currentElement)) {
            currentAccount = new Accounts();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty() && currentAccount != null) {
            switch (currentElement) {
                case "fromDate":
                    currentAccount.setFromDate(Date.valueOf(value));
                    break;
                case "toDate":
                    currentAccount.setToDate(Date.valueOf(value));
                    break;
                case "balance":
                    currentAccount.setBalance(Integer.parseInt(value));
                    break;
                case "currency":
                    currentAccount.setCurrency(value);
                    break;
                case "client":
                    currentAccount.setClient(CS.get(Integer.parseInt(value)));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("account".equals(qName)) {
            if (accounts == null) {
                accounts = new ArrayList<>();
            }
            accounts.add(currentAccount);
            currentAccount = null;
        }
    }
}