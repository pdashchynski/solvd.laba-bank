package com.solvd.laba.menu.tables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.database.model.Bank;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.database.model.Passports;
import com.solvd.laba.database.model.Persons;
import com.solvd.laba.parsing.xml.sax.SAXParser;
import com.solvd.laba.parsing.xml.sax.handlers.ClientsSAXHandler;
import com.solvd.laba.parsing.xml.sax.handlers.PersonsSAXHandler;
import com.solvd.laba.services.PersonsService;
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

public class PersonsMenu {

    private static final Logger LOGGER = LogManager.getLogger(PersonsMenu.class);
    private final Scanner sc;
    private final PersonsService ps;

    public PersonsMenu() {
        this.ps = new PersonsService();
        this.sc = new Scanner(System.in);
    }

    public void display() {
        boolean isExit = false;
        int input;
        while (!isExit) {
            CRUDMenu.display("Persons");
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
        LOGGER.info(ps.get(id).toString());
    }

    private void printAll() {
        LOGGER.info(ps.getAll().toString());
    }
    
    private List<Persons> create() {
        boolean isExit = false;
        int input;
        List<Persons> persons = null;
        while (!isExit) {
            LOGGER.info("1 - Enter From Console");
            LOGGER.info("2 - Parse XML(SAX)");
            LOGGER.info("3 - Parse XML(JAXB)");
            LOGGER.info("4 - Parse JSON");
            LOGGER.info("0 - Quit");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    assert persons != null;
                    persons.add(createFromConsole());
                    break;
                case 2:
                    persons = createUsingSAX();
                    break;
                case 3:
                    persons = createUsingJAXB();
                    break;
                case 4:
                    persons = createFromJSON();
                    break;
                case 0:
                    isExit = true;
                    LOGGER.info("Back to Persons Menu");
                    break;
                default:
                    LOGGER.info("Incorrect Menu Input");
                    break;
            }
        }
        return persons;
    }

    private Persons createFromConsole() {
        LOGGER.info("Enter ID");
        int id = sc.nextInt();
        LOGGER.info("Enter First Name");
        String firstName = sc.nextLine();
        LOGGER.info("Enter Middle Name");
        String middleName = sc.nextLine();
        LOGGER.info("Enter Last Name");
        String lastName = sc.nextLine();
        LOGGER.info("Enter Age");
        short age = sc.nextShort();
        LOGGER.info("Enter From Date (YYYY-MM-DD)");
        Date dateOfBirth = Date.valueOf(sc.nextLine());
        LOGGER.info("Enter Gender");
        String gender = sc.nextLine();
        LOGGER.info("Enter Address ID");
        int addressId = sc.nextInt();
        return ps.create(id, firstName, middleName, lastName, age,
                dateOfBirth, gender, addressId);
    }

    private List<Persons> createUsingSAX() {
        List<Persons> personsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        PersonsSAXHandler personsSAXHandler = new PersonsSAXHandler();
        SAXParser saxParser = new SAXParser(personsSAXHandler);

        try {
            saxParser.parse(path);
            List<Persons> persons = personsSAXHandler.getPersons();
            if (persons != null && !persons.isEmpty()) {
                for (Persons person : persons) {
                    personsReturn.add(ps.create(
                            person.getId(),
                            person.getFirstName(),
                            person.getMiddleName(),
                            person.getLastName(),
                            person.getAge(),
                            person.getDateOfBirth(),
                            person.getGender(),
                            person.getAddress().getId()
                    ));
                }
                LOGGER.info("Created successfully from an XML file.");
            } else {
                LOGGER.info("No relevant data found in the XML file.");
            }
        } catch (Exception e) {
            LOGGER.error("Error parsing XML file: " + e.getMessage());
        }
        return personsReturn;
    }

    private List<Persons> createUsingJAXB() {
        List<Persons> personsReturn = new ArrayList<>();
        LOGGER.info("Enter XML file name");
        String name = sc.nextLine();
        String path = "src/main/resources/xml/" + name + ".xml";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bank.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Bank bank = (Bank) unmarshaller.unmarshal(new FileReader(path));

            List<Persons> persons = bank.getPersons();

            for (Persons person : persons) {
                personsReturn.add(ps.create(
                        person.getId(),
                        person.getFirstName(),
                        person.getMiddleName(),
                        person.getLastName(),
                        person.getAge(),
                        person.getDateOfBirth(),
                        person.getGender(),
                        person.getAddress().getId()
                ));
            }
            LOGGER.info("Created successfully from an XML file");
        } catch (JAXBException | FileNotFoundException e) {
            LOGGER.info("Creation failed: " + e.getMessage());
        }
        return personsReturn;
    }

    private List<Persons> createFromJSON() {
        List<Persons> personsReturn = new ArrayList<>();
        LOGGER.info("Enter JSON's name");
        String name = sc.nextLine();
        String path = "src/main/resources/json/" + name + ".json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File(path);

            JsonNode jsonNode = objectMapper.readTree(file);

            if (jsonNode.isArray()) {
                List<Persons> persons = objectMapper.readValue(file, new TypeReference<>() {});

                for (Persons person : persons) {
                    personsReturn.add(ps.create(
                            person.getId(),
                            person.getFirstName(),
                            person.getMiddleName(),
                            person.getLastName(),
                            person.getAge(),
                            person.getDateOfBirth(),
                            person.getGender(),
                            person.getAddress().getId()
                    ));
                }
            } else {
                Persons person = objectMapper.readValue(file, Persons.class);
                personsReturn.add(ps.create(
                        person.getId(),
                        person.getFirstName(),
                        person.getMiddleName(),
                        person.getLastName(),
                        person.getAge(),
                        person.getDateOfBirth(),
                        person.getGender(),
                        person.getAddress().getId()
                ));
            }
        } catch (IOException e) {
            LOGGER.info(e);
        }
        return personsReturn;
    }

    private void save() {
        for (Persons person : create()) {
            ps.save(person);
        }
    }

    private void insert() {
        for (Persons person : create()) {
            ps.insert(person);
        }
    }

    private void update() {
        for (Persons person : create()) {
            ps.update(person);
        }
    }

    private void delete() {
        LOGGER.info("Enter ID");
        int id = Integer.parseInt(sc.nextLine());
        ps.delete(id);
    }
}
