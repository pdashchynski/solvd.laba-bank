package com.solvd.laba.menu.tables;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.database.model.Bank;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.parsing.xml.sax.SAXParser;
import com.solvd.laba.parsing.xml.sax.handlers.AccountsSAXHandler;
import com.solvd.laba.parsing.xml.sax.handlers.PersonsSAXHandler;
import com.solvd.laba.services.AccountsService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountsMenu {

    private static final Logger LOGGER = LogManager.getLogger(AccountsMenu.class);
    private final Scanner sc;
    private final AccountsService as;

    public AccountsMenu() {
        this.as = new AccountsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Accounts");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    print();
                    break;
                case 2:
                    printAll();
                    break;
                case 3:
                    save();
                    break;
                case 4:
                    insert();
                    break;
                case 5:
                    update();
                    break;
                case 6:
                    delete();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Tables Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
    }

    private void print() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        LOGGER.info(as.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(as.getAll().toString());
    }

    private List<Accounts> create() {
        boolean isExit = false;
        int input;
        List<Accounts> accounts = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    assert accounts != null;
                    accounts.add(createFromConsole());
                    break;
                case 2:
                    accounts = createUsingSAX();
                    break;
                case 3:
                    accounts = createUsingJAXB();
                    break;
                case 4:
                    accounts = createFromJSON();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Accounts Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
        return accounts;
    }

    private Accounts createFromConsole() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date fromDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter To Date (YYYY-MM-DD)");
        Date toDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Balance");
        int balance = sc.nextInt();
        LOGGER.info("Enter Currency");
        String currency = sc.nextLine();
        LOGGER.info("Enter Client ID");
        int clientId = sc.nextInt();
        return as.create(id, fromDate, toDate, balance, currency, clientId);
    }

    private List<Accounts> createUsingSAX() {
        List<Accounts> accountsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        AccountsSAXHandler accountsSAXHandler = new AccountsSAXHandler();
        SAXParser saxParser = new SAXParser(accountsSAXHandler);

        try {
            saxParser.parse(path);
            List<Accounts> accounts = accountsSAXHandler.getAccounts();
            if (accounts != null && !accounts.isEmpty()) {
                for (Accounts account : accounts) {
                    accountsReturn.add(as.create(
                            account.getId(),
                            account.getFromDate(),
                            account.getToDate(),
                            account.getBalance(),
                            account.getCurrency(),
                            account.getClient().getId()
                    ));
                }
                LOGGER.info("Created successfully from an XML file.");
            } else {
                LOGGER.info("No relevant data found in the XML file.");
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing XML file: " + e.getMessage());
        }
        return accountsReturn;
    }

    private List<Accounts> createUsingJAXB() {
        List<Accounts> accountsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Bank bank = (Bank) unmarshaller.unmarshal(new FileReader(path));

            List<Accounts> accounts = bank.getAccounts();

            for (Accounts account : accounts) {
                accountsReturn.add(as.create(
                        account.getId(),
                        account.getFromDate(),
                        account.getToDate(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getClient().getId()
                ));
            }
            LOGGER.info("Created successfully from an XML file");
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info("Creation failed: " + e.getMessage());
        }
        return accountsReturn;
    }

    private List<Accounts> createFromJSON() {
        List<Accounts> accountsReturn = new ArrayList<>();
        LOGGER.info("Enter JSON's name");
        String name = sc.nextLine();
        String path = "src/main/resources/json/" + name + ".json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.isArray()) {
                List<Accounts> accounts = objectMapper.readValue(file, new TypeReference<>() {});

                for (Accounts account : accounts) {
                    accountsReturn.add(as.create(
                            account.getId(),
                            account.getFromDate(),
                            account.getToDate(),
                            account.getBalance(),
                            account.getCurrency(),
                            account.getClient().getId()
                    ));
                }
            } else {
                Accounts account = objectMapper.readValue(file, Accounts.class);
                accountsReturn.add(as.create(
                        account.getId(),
                        account.getFromDate(),
                        account.getToDate(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getClient().getId()
                ));
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return accountsReturn;
    }

    private void save() {
        for (Accounts account : create()) {
            as.save(account);
        }
    }

    private void insert() {
        for (Accounts account : create()) {
            as.insert(account);
        }
    }

    private void update() {
        for (Accounts account : create()) {
            as.update(account);
        }
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        as.delete(id);
    }
}