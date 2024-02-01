package com.solvd.laba.menu.tables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.database.model.Bank;
import com.solvd.laba.database.model.Cards;
import com.solvd.laba.parsing.xml.sax.SAXParser;
import com.solvd.laba.parsing.xml.sax.handlers.AddressesSAXHandler;
import com.solvd.laba.parsing.xml.sax.handlers.CardsSAXHandler;
import com.solvd.laba.services.CardsService;
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

public class CardsMenu {

    private static final Logger LOGGER = LogManager.getLogger(CardsMenu.class);
    private final Scanner sc;
    private final CardsService cs;

    public CardsMenu() {
        this.cs = new CardsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Cards");
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
        LOGGER.info(cs.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(cs.getAll().toString());
    }
    
    private List<Cards> create() {
        boolean isExit = false;
        int input;
        List<Cards> cards = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    assert cards != null;
                    cards.add(createFromConsole());
                    break;
                case 2:
                    cards = createUsingSAX();
                    break;
                case 3:
                    cards = createUsingJAXB();
                    break;
                case 4:
                    cards = createFromJSON();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Cards Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
        return cards;
    }

    private Cards createFromConsole() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date fromDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter To Date (YYYY-MM-DD)");
        Date toDate = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Type");
        String type = sc.nextLine();
        LOGGER.info("Enter Account ID");
        int accountId = sc.nextInt();
        return cs.create(id, fromDate, toDate, type, accountId);
    }

    private List<Cards> createUsingSAX() {
        List<Cards> cardsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        CardsSAXHandler cardsSAXHandler = new CardsSAXHandler();
        SAXParser saxParser = new SAXParser(cardsSAXHandler);

        try {
            saxParser.parse(path);
            List<Cards> cards = cardsSAXHandler.getCards();
            if (cards != null && !cards.isEmpty()) {
                for (Cards card : cards) {
                    cardsReturn.add(cs.create(
                            card.getId(),
                            card.getFromDate(),
                            card.getToDate(),
                            card.getType(),
                            card.getAccount().getId()
                    ));
                }
                LOGGER.info("Created successfully from an XML file.");
            } else {
                LOGGER.info("No relevant data found in the XML file.");
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing XML file: " + e.getMessage());
        }
        return cardsReturn;
    }

    private List<Cards> createUsingJAXB() {
        List<Cards> cardsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Bank bank = (Bank) unmarshaller.unmarshal(new FileReader(path));

            List<Cards> cards = bank.getCards();

            for (Cards card : cards) {
                cardsReturn.add(cs.create(
                        card.getId(),
                        card.getFromDate(),
                        card.getToDate(),
                        card.getType(),
                        card.getAccount().getId()
                ));
            }
            LOGGER.info("Created successfully from an XML file");
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info("Creation failed: " + e.getMessage());
        }
        return cardsReturn;
    }

    private List<Cards> createFromJSON() {
        List<Cards> cardsReturn = new ArrayList<>();
        LOGGER.info("Enter JSON's name");
        String name = sc.nextLine();
        String path = "src/main/resources/json/" + name + ".json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.isArray()) {
                List<Cards> cards = objectMapper.readValue(file, new TypeReference<>() {});

                for (Cards card : cards) {
                    cardsReturn.add(cs.create(
                            card.getId(),
                            card.getFromDate(),
                            card.getToDate(),
                            card.getType(),
                            card.getAccount().getId()
                    ));
                }
            } else {
                Cards card = objectMapper.readValue(file, Cards.class);
                cardsReturn.add(cs.create(
                        card.getId(),
                        card.getFromDate(),
                        card.getToDate(),
                        card.getType(),
                        card.getAccount().getId()
                ));
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return cardsReturn;
    }

    private void save() {
        for (Cards card : create()) {
            cs.save(card);
        }
    }

    private void insert() {
        for (Cards card : create()) {
            cs.insert(card);
        }
    }

    private void update() {
        for (Cards card : create()) {
            cs.update(card);
        }
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        cs.delete(id);
    }
}