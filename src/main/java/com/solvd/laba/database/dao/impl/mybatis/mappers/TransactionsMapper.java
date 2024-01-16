package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Transactions;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TransactionsMapper {

    @Select("SELECT * FROM transactions WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "dateTime", column = "date_time"),
            @Result(property = "staff", column = "staff_id"),
            @Result(property = "service", column = "service_id")
    })
    Transactions get(int id);

    @Select("SELECT * FROM transactions")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "type", column = "type"),
            @Result(property = "amount", column = "amount"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "dateTime", column = "date_time"),
            @Result(property = "staff", column = "staff_id"),
            @Result(property = "service", column = "service_id")
    })
    List<Transactions> getAll();

    @Insert("INSERT INTO transactions (id, type, amount, currency, date_time, staff_id, services_id) " +
            "VALUES (#{id}, #{type}, #{amount}, #{currency}, #{dateTime}, #{staff.id}, #{service.id}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE transactions SET from_date = new.from_date, to_date = new.to_date, " +
            "balance = new.balance, currency = new.currency, " +
            "clients_id = new.clients_id, clients_persons_id = new.clients_persons_id")
    void save(Transactions transaction);

    @Insert("INSERT INTO transactions (id, type, amount, currency, date_time, staff_id, services_id) " +
            "VALUES (#{id}, #{type}, #{amount}, #{currency}, #{dateTime}, #{staff.id}, #{service.id})")
    void insert(Transactions transaction);

    @Update("UPDATE transactions SET " +
            "type = #{type}, " +
            "amount = #{amount}, " +
            "currency = #{currency}, " +
            "date_time = #{dateTime}, " +
            "staff_id = #{staff.id}, " +
            "services_id = #{service.id} " +
            "WHERE id = #{id}")
    void update(Transactions transaction);

    @Delete("DELETE FROM transactions WHERE id = #{id}")
    void delete(int id);
}