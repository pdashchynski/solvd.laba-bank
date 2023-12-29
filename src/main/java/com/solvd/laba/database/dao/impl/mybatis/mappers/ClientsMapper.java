package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Clients;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClientsMapper {

    @Select("SELECT * FROM clients WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "person", column = "persons_id"),
            @Result(property = "status", column = "status"),
    })
    Clients get(int id);

    @Select("SELECT * FROM clients")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "person", column = "persons_id"),
            @Result(property = "status", column = "status"),
    })
    List<Clients> getAll();

    @Insert("INSERT INTO clients (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{}, #{}, #{}, #{}, #{}, #{}, #{}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE clients SET from_date = new.from_date, to_date = new.to_date, " +
            "balance = new.balance, currency = new.currency, " +
            "clients_id = new.clients_id, clients_persons_id = new.clients_persons_id")
    void save(Clients client);

    @Insert("INSERT INTO clients (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{}, #{}, #{}, #{}, #{}, #{}, #{})")
    void insert(Clients client);

    @Update("UPDATE clients SET " +
            "from_date = #{}, " +
            "to_date = #{}, " +
            "balance = #{}, " +
            "currency = #{}, " +
            "clients_id = #{}, " +
            "clients_persons_id = #{} " +
            "WHERE id = #{}")
    void update(Clients client);

    @Delete("DELETE FROM clients WHERE id = #{}")
    void delete(int id);
}
