package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Passports;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PassportsMapper {

    @Select("SELECT * FROM passports WHERE id = #{}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get"))
    })
    Passports get(int id);

    @Select("SELECT * FROM passports")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fromDate", column = "from_date"),
            @Result(property = "toDate", column = "to_date"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get"))
    })
    List<Passports> getAll();

    @Insert("INSERT INTO passports (id, from_date, to_date, persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{person.id}) AS new" +
            "ON DUPLICATE KEY " +
            "UPDATE passports SET from_date = new.from_date, " +
            "to_date = new.to_date, persons_id = new.persons_id")
    void save(Passports passport);

    @Insert("INSERT INTO passports (id, from_date, to_date, persons_id) " +
            "VALUES (#{id}, #{fromDate}, #{toDate}, #{person.id})")
    void insert(Passports passport);

    @Update("UPDATE passports SET " +
            "from_date = #{fromDate}, " +
            "to_date = #{toDate}, " +
            "persons_id = #{person.id}, " +
            "WHERE id = #{id}")
    void update(Passports passport);

    @Delete("DELETE FROM passports WHERE id = #{id}")
    void delete(int id);
}
