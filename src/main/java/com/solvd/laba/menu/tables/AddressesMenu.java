package com.solvd.laba.menu.tables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.database.model.Bank;
import com.solvd.laba.parsing.xml.sax.SAXParser;
import com.solvd.laba.parsing.xml.sax.handlers.AddressesSAXHandler;
import com.solvd.laba.services.AddressesService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressesMenu {

    private static final Logger LOGGER = LogManager.getLogger(AddressesMenu.class);
    private final Scanner sc;
    private final AddressesService as;

    public AddressesMenu() {
        this.as = new AddressesService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Addresses");
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

    private List<Addresses> create() {
        boolean isExit = false;
        int input;
        List<Addresses> addresses = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    assert addresses != null;
                    addresses.add(createFromConsole());
                    break;
                case 2:
                    addresses = createUsingSAX();
                    break;
                case 3:
                    addresses = createUsingJAXB();
                    break;
                case 4:
                    addresses = createFromJSON();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Addresses Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
        return addresses;
    }

    private Addresses createFromConsole() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        LOGGER.info("Enter Country");
        String country = sc.nextLine();
        LOGGER.info("Enter City");
        String city = sc.nextLine();
        LOGGER.info("Enter Postal Code");
        String postalCode = sc.nextLine();
        return as.create(id, country, city, postalCode);
    }

    private List<Addresses> createUsingSAX() {
        List<Addresses> addressesReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        AddressesSAXHandler addressesSAXHandler = new AddressesSAXHandler();
        SAXParser saxParser = new SAXParser(addressesSAXHandler);

        try {
            saxParser.parse(path);
            List<Addresses> addresses = addressesSAXHandler.getAddresses();
            if (addresses != null && !addresses.isEmpty()) {
                for (Addresses address : addresses) {
                    addressesReturn.add(as.create(
                            address.getId(),
                            address.getCountry(),
                            address.getCity(),
                            address.getPostalCode()
                    ));
                }
                LOGGER.info("Created successfully from an XML file.");
            } else {
                LOGGER.info("No relevant data found in the XML file.");
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing XML file: " + e.getMessage());
        }
        return addressesReturn;
    }

    private List<Addresses> createUsingJAXB() {
        List<Addresses> addressesReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Bank bank = (Bank) unmarshaller.unmarshal(new FileReader(path));

            List<Addresses> addresses = bank.getAddresses();

            for (Addresses address : addresses) {
                addressesReturn.add(as.create(
                        address.getId(),
                        address.getCountry(),
                        address.getCity(),
                        address.getPostalCode()
                ));
            }
            LOGGER.info("Created successfully from an XML file");
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info("Creation failed: " + e.getMessage());
        }
        return addressesReturn;
    }

    private List<Addresses> createFromJSON() {
        List<Addresses> addressesReturn = new ArrayList<>();
        LOGGER.info("Enter JSON's name");
        String name = sc.nextLine();
        String path = "src/main/resources/json/" + name + ".json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.isArray()) {
                List<Addresses> addresses = objectMapper.readValue(file, new TypeReference<>() {});

                for (Addresses address : addresses) {
                    addressesReturn.add(as.create(address.getId(), address.getCountry(), address.getCity(), address.getPostalCode()));
                }
            } else {
                Addresses address = objectMapper.readValue(file, Addresses.class);
                addressesReturn.add(as.create(address.getId(), address.getCountry(), address.getCity(), address.getPostalCode()));
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return addressesReturn;
    }

    private void save() {
        for (Addresses address : create()) {
            as.save(address);
        }
    }

    private void insert() {
        for (Addresses address : create()) {
            as.insert(address);
        }
    }

    private void update() {
        for (Addresses address : create()) {
            as.update(address);
        }
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        as.delete(id);
    }
}
