package com.solvd.laba.parsing.xml.jaxb;

import com.solvd.laba.database.model.Persons;
import com.solvd.laba.services.PersonsService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class PersonsAdapter extends XmlAdapter<Long, Persons> {

    private final PersonsService service = new PersonsService();

    @Override
    public Persons unmarshal(Long id) throws Exception {
        return service.get(Math.toIntExact(id));
    }

    @Override
    public Long marshal(Persons person) {
        return Long.valueOf((person != null) ? person.getId() : null);
    }
}
