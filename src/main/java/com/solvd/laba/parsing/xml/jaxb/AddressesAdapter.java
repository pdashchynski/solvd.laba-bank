package com.solvd.laba.parsing.xml.jaxb;

import com.solvd.laba.database.model.Addresses;
import com.solvd.laba.services.AddressesService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AddressesAdapter extends XmlAdapter<Long, Addresses> {

    private final AddressesService service = new AddressesService();

    @Override
    public Addresses unmarshal(Long id) throws Exception {
        return service.get(Math.toIntExact(id));
    }

    @Override
    public Long marshal(Addresses address) {
        return Long.valueOf((address != null) ? address.getId() : null);
    }
}
