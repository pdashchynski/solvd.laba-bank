package com.solvd.laba.parsing.xml.jaxb;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String marshal(Date v) {
        return new SimpleDateFormat(CUSTOM_FORMAT_STRING).format(v);
    }

    @Override
    public Date unmarshal(String v) throws ParseException {
        return (Date) new SimpleDateFormat(CUSTOM_FORMAT_STRING).parse(v);
    }
}