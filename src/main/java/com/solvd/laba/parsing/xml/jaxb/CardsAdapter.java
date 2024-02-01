package com.solvd.laba.parsing.xml.jaxb;

import com.solvd.laba.database.model.Cards;
import com.solvd.laba.services.CardsService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class CardsAdapter extends XmlAdapter<Long, Cards> {

    private final CardsService service = new CardsService();

    @Override
    public Cards unmarshal(Long id) throws Exception {
        return service.get(Math.toIntExact(id));
    }

    @Override
    public Long marshal(Cards card) {
        return Long.valueOf((card != null) ? card.getId() : null);
    }
}
