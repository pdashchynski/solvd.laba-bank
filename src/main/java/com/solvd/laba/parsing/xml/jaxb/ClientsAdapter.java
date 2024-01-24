package com.solvd.laba.parsing.xml.jaxb;

import com.solvd.laba.database.model.Clients;
import com.solvd.laba.services.ClientsService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class ClientsAdapter extends XmlAdapter<Long, Clients> {

    private final ClientsService service = new ClientsService();

    @Override
    public Clients unmarshal(Long id) throws Exception {
        return service.get(Math.toIntExact(id));
    }

    @Override
    public Long marshal(Clients client) {
        return Long.valueOf((client != null) ? client.getId() : null);
    }
}
