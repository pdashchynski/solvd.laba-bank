package com.solvd.laba.parsing.xml.jaxb;

import com.solvd.laba.database.model.Accounts;
import com.solvd.laba.services.AccountsService;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AccountsAdapter extends XmlAdapter<Long, Accounts> {

    private final AccountsService service = new AccountsService();

    @Override
    public Accounts unmarshal(Long id) throws Exception {
        return service.get(Math.toIntExact(id));
    }

    @Override
    public Long marshal(Accounts account) {
        return Long.valueOf((account != null) ? account.getId() : null);
    }
}
