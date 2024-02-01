package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Clients;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ClientsMapper {

    @Select("SELECT * FROM clients WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get")),
            @Result(property = "status", column = "status"),
    })
    Clients get(int id);

    @Select("SELECT * FROM clients")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get")),
            @Result(property = "status", column = "status"),
    })
    List<Clients> getAll();

    @Insert("INSERT INTO clients (id, persons_id, status) " +
            "VALUES (#{id}, #{person.id},  #{status}) AS new " +
            "ON DUPLICATE KEY " +
            "UPDATE persons_id = new.persons_id, status = new.status")
    void save(Clients client);

    @Insert("INSERT INTO clients (id, persons_id, status) " +
            "VALUES (#{id}, #{person.id},  #{status})")
    void insert(Clients client);

    @Update("UPDATE clients SET " +
            "persons_id = #{person.id}, " +
            "status = #{status}, " +
            "WHERE id = #{id}")
    void update(Clients client);

    @Delete("DELETE FROM clients WHERE id = #{id}")
    void delete(int id);
}
