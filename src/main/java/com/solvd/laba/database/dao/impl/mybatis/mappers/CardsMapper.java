package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Cards;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CardsMapper {

    @Select("SELECT * FROM cards WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "type", column = "type"),
            @Result(property = "account", column = "accounts_id")
    })
    Cards get(int id);

    @Select("SELECT * FROM cards")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "type", column = "type"),
            @Result(property = "accounts", column = "accounts_id")
    })
    List<Cards> getAll();

    @Insert("INSERT INTO cards (id, from_date, to_date, type, " +
            "accounts_id, accounts_clients_id, accounts_clients_persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{type}, " +
            "#{account.id}, #{account.clients_id}, #{}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE cards SET from_date = new.from_date, to_date = new.to_date, " +
            "type = new.type, " +
            "accounts_id = new.accounts_id, " +
            "accounts_clients_id = new.accounts_clients_id, " +
            "accounts_clients_persons_id = new.accounts_clients_persons_id")
    void save(Cards card);

    @Insert("INSERT INTO cards (id, from_date, to_date, type, " +
            "accounts_id, accounts_clients_id, accounts_clients_persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{type}, " +
            "#{account.id}, #{account.clients_id}, #{})")
    void insert(Cards card);

    @Update("UPDATE cards SET " +
            "from_date = #{fromDate}, " +
            "to_date = #{toDate}, " +
            "type = #{type}, " +
            "accounts_id = #{account.id}, " +
            "accounts_clients_id = #{account.clients_id}, " +
            "accounts_clients_persons_id = #{} " +
            "WHERE id = #{}")
    void update(Cards card);

    @Delete("DELETE FROM cards WHERE id = #{id}")
    void delete(int id);
}
