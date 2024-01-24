package com.solvd.laba.menu.tables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.database.model.Bank;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.database.model.Clients;
import com.solvd.laba.parsing.xml.sax.SAXParser;
import com.solvd.laba.parsing.xml.sax.handlers.CardsSAXHandler;
import com.solvd.laba.parsing.xml.sax.handlers.ClientsSAXHandler;
import com.solvd.laba.services.ClientsService;
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

public class ClientsMenu {

    private static final Logger LOGGER = LogManager.getLogger(ClientsMenu.class);
    private final Scanner sc;
    private final ClientsService cs;

    public ClientsMenu() {
        this.cs = new ClientsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Clients");
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

    private List<Clients> create() {
        boolean isExit = false;
        int input;
        List<Clients> clients = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    assert clients != null;
                    clients.add(createFromConsole());
                    break;
                case 2:
                    clients = createUsingSAX();
                    break;
                case 3:
                    clients = createUsingJAXB();
                    break;
                case 4:
                    clients = createFromJSON();
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
        return clients;
    }

    private Clients createFromConsole() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter Person ID");
        int personId = sc.nextInt();
        LOGGER.info("Enter Status");
        String status = sc.nextLine();
        return cs.create(id, personId, status);
    }

    private List<Clients> createUsingSAX() {
        List<Clients> clientsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        ClientsSAXHandler clientsSAXHandler = new ClientsSAXHandler();
        SAXParser saxParser = new SAXParser(clientsSAXHandler);

        try {
            saxParser.parse(path);
            List<Clients> clients = clientsSAXHandler.getClients();
            if (clients != null && !clients.isEmpty()) {
                for (Clients client : clients) {
                    clientsReturn.add(cs.create(
                            client.getId(),
                            client.getPerson().getId(),
                            client.getStatus()
                    ));
                }
                LOGGER.info("Created successfully from an XML file.");
            } else {
                LOGGER.info("No relevant data found in the XML file.");
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing XML file: " + e.getMessage());
        }
        return clientsReturn;
    }

    private List<Clients> createUsingJAXB() {
        List<Clients> clientsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Bank bank = (Bank) unmarshaller.unmarshal(new FileReader(path));

            List<Clients> clients = bank.getClients();

            for (Clients client : clients) {
                clientsReturn.add(cs.create(
                        client.getId(),
                        client.getPerson().getId(),
                        client.getStatus()
                ));
            }
            LOGGER.info("Created successfully from an XML file");
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info("Creation failed: " + e.getMessage());
        }
        return clientsReturn;
    }

    private List<Clients> createFromJSON() {
        List<Clients> clientsReturn = new ArrayList<>();
        LOGGER.info("Enter JSON's name");
        String name = sc.nextLine();
        String path = "src/main/resources/json/" + name + ".json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.isArray()) {
                List<Clients> clients = objectMapper.readValue(file, new TypeReference<>() {});

                for (Clients client : clients) {
                    clientsReturn.add(cs.create(
                            client.getId(),
                            client.getPerson().getId(),
                            client.getStatus()
                    ));
                }
            } else {
                Clients client = objectMapper.readValue(file, Clients.class);
                clientsReturn.add(cs.create(
                        client.getId(),
                        client.getPerson().getId(),
                        client.getStatus()
                ));
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return clientsReturn;
    }

    private void save() {
        for (Clients client : create()) {
            cs.save(client);
        }
    }

    private void insert() {
        for (Clients client : create()) {
            cs.insert(client);
        }
    }

    private void update() {
        for (Clients client : create()) {
            cs.update(client);
        }
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        cs.delete(id);
    }
}
