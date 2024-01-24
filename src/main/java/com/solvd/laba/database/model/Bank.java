package com.solvd.laba.database.model;

import com.solvd.laba.parsing.xml.jaxb.DateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.List;

@XmlRootElement(name = "bank")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bank {

    @XmlElementWrapper(name = "accounts")
    @XmlElement(name = "account")
    private List<Accounts> accounts;

    @XmlElementWrapper(name = "addresses")
    @XmlElement(name = "address")
    private List<Addresses> addresses;

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<Cards> cards;

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Clients> clients;

    @XmlElementWrapper(name = "persons")
    @XmlElement(name = "person")
    private List<Persons> persons;

    public Bank() {}

    public Bank(List<Accounts> accounts, List<Addresses> addresses, List<Cards> cards, List<Clients> clients, List<Persons> persons) {
        this.accounts = accounts;
        this.addresses = addresses;
        this.cards = cards;
        this.clients = clients;
        this.persons = persons;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public List<Addresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }

    public List<Clients> getClients() {
        return clients;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
    }
}
