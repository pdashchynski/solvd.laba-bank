package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Accounts;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountsMapper {

    @Select("SELECT * FROM accounts WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "client", column = "clients_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.ClientsMapper.get"))
    })
    Accounts get(int id);

    @Select("SELECT * FROM accounts")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "currency", column = "currency"),
            @Result(property = "client", column = "clients_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.ClientsMapper.get"))
    })
    List<Accounts> getAll();

    @Insert("INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{balance}, " +
            "#{currency}, #{client.id}, #{client.persons_id}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE accounts SET from_date = new.from_date, to_date = new.to_date, " +
            "balance = new.balance, currency = new.currency, " +
            "clients_id = new.clients_id, clients_persons_id = new.clients_persons_id")
    void save(Accounts account);

    @Insert("INSERT INTO accounts (id, from_date, to_date, balance, currency, " +
            "clients_id, clients_persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{balance}, #{currency}, #{client.id}, #{client.persons_id})")
    void insert(Accounts account);

    @Update("UPDATE accounts SET " +
            "from_date = #{fromDate}, " +
            "to_date = #{toDate}, " +
            "balance = #{balance}, " +
            "currency = #{currency}, " +
            "clients_id = #{client.id}, " +
            "clients_persons_id = #{client.persons_id} " +
            "WHERE id = #{id}")
    void update(Accounts account);

    @Delete("DELETE FROM accounts WHERE id = #{id}")
    void delete(int id);
}
