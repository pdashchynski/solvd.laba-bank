package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Accounts;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StaffMapper {

    @Select("SELECT * FROM accounts WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "client", column = "clients_id")
    })
    Accounts get(int id);

    @Select("SELECT * FROM accounts")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "client", column = "clients_id")
    })
    List<Accounts> getAll();

    @Insert("INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{}, #{}, #{}, #{}, #{}, #{}, #{}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE accounts SET from_date = new.from_date, to_date = new.to_date, " +
            "balance = new.balance, currency = new.currency, " +
            "clients_id = new.clients_id, clients_persons_id = new.clients_persons_id")
    void save(Accounts account);

    @Insert("INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{}, #{}, #{}, #{}, #{}, #{}, #{})")
    void insert(Accounts account);

    @Update("UPDATE accounts SET " +
            "from_date = #{}, " +
            "to_date = #{}, " +
            "balance = #{}, " +
            "currency = #{}, " +
            "clients_id = #{}, " +
            "clients_persons_id = #{} " +
            "WHERE id = #{}")
    void update(Accounts account);

    @Delete("DELETE FROM accounts WHERE id = #{}")
    void delete(int id);
}
