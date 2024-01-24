package com.solvd.laba.parsing.xml.sax.handlers;

import com.solvd.laba.database.model.Cards;
import com.solvd.laba.services.AccountsService;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CardsSAXHandler extends DefaultHandler {


    private List<Cards> cards;
    private Cards currentCard;
    private String currentElement;
    private static final AccountsService AS = new AccountsService();

    public List<Cards> getCards() {
        return cards;
    }

    public Cards getCurrentCard() {
        return currentCard;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;

        if ("card".equals(currentElement)) {
            currentCard = new Cards();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).trim();

        if (!value.isEmpty() && currentCard != null) {
            switch (currentElement) {
                case "fromDate":
                    currentCard.setFromDate(Date.valueOf(value));
                    break;
                case "toDate":
                    currentCard.setToDate(Date.valueOf(value));
                    break;
                case "balance":
                    currentCard.setType(value);
                    break;
                case "accountId":
                    currentCard.setAccount(AS.get(Integer.parseInt(value)));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("card".equals(qName)) {
            if (cards == null) {
                cards = new ArrayList<>();
            }
            cards.add(currentCard);
            currentCard = null;
        }
    }
}