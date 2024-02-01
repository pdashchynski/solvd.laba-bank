package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Persons;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonsMapper {

    @Select("SELECT * FROM persons WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "middleName", column = "middle_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "dateOfBirth", column = "date_of_birth"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "address", column = "addresses_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.AddressesMapper.get"))
    })
    Persons get(int id);

    @Select("SELECT * FROM persons")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "middleName", column = "middle_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "age", column = "age"),
            @Result(property = "dateOfBirth", column = "date_of_birth"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "address", column = "addresses_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.AddressesMapper.get"))
    })
    List<Persons> getAll();

    @Insert("INSERT INTO persons (id, first_name, middle_name, last_name, age, date_of_birth, gender, addresses_id) " +
            "VALUES (#{id}, #{firstName}, #{middleName}, #{lastName}, #{age}, #{dateOfBirth}, #{gender}, #{address.id}) AS new " +
            "ON DUPLICATE KEY " +
            "UPDATE first_name = new.first_name, " +
            "middle_name = new.middle_name, last_name = new.last_name, age = new.age, " +
            "date_of_birth = new.date_of_birth, gender = new.gender, addresses_id = new.addresses_id")
    void save(Persons person);

    @Insert("INSERT INTO persons (id, first_name, middle_name, last_name, age, date_of_birth, gender, addresses_id) " +
            "VALUES (#{id}, #{firstName}, #{middleName}, #{lastName}, #{age}, #{dateOfBirth}, #{gender}, #{address.id})")
    void insert(Persons person);

    @Update("UPDATE persons SET " +
            "first_name = #{firstName}, " +
            "middle_name = #{middleName}, " +
            "last_name = #{lastName}, " +
            "age = #{age}, " +
            "date_of_birth = #{dateOfBirth}, " +
            "gender = #{gender} " +
            "addresses_id = #{address.id} " +
            "WHERE id = #{id}")
    void update(Persons person);

    @Delete("DELETE FROM persons WHERE id = #{id}")
    void delete(int id);
}
