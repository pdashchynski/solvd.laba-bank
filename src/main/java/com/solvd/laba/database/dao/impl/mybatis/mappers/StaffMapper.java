package com.solvd.laba.database.dao.impl.mybatis.mappers;

import com.solvd.laba.database.model.Staff;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StaffMapper {

    @Select("SELECT * FROM staff WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dateHired", column = "date_hired"),
            @Result(property = "position", column = "position"),
            @Result(property = "salary", column = "salary"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get")),
            @Result(property = "department", column = "departments_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.DepartmentsMapper.get"))
    })
    Staff get(int id);

    @Select("SELECT * FROM staff")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dateHired", column = "date_hired"),
            @Result(property = "position", column = "position"),
            @Result(property = "salary", column = "salary"),
            @Result(property = "person", column = "persons_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.PersonsMapper.get")),
            @Result(property = "department", column = "departments_id",
                    one = @One(select = "com.solvd.laba.database.dao.impl.mybatis.mappers.DepartmentsMapper.get"))
    })
    List<Staff> getAll();

    @Insert("INSERT INTO staff (id, date_hired, position, salary, persons_id, departments_id) " +
            "VALUES (#{id}, #{dateHired}, #{position}, #{salary}, #{person.id}, #{department.id}) AS new " +
            "ON DUPLICATE KEY " +
            "UPDATE date_hired = new.date_hired, position = new.position " +
            "salary = new.salary, persons_id = new.persons_id, departments_id = new.departments_id")
    void save(Staff staff);

    @Insert("INSERT INTO staff (id, date_hired, position, salary, persons_id, departments_id) " +
            "VALUES (#{id}, #{dateHired}, #{position}, #{salary}, #{person.id}, #{department.id})")
    void insert(Staff staff);

    @Update("UPDATE staff SET " +
            "date_hired = #{dateHired}, " +
            "position = #{position}, " +
            "salary = #{salary}, " +
            "persons_id = #{person.id}, " +
            "departments_id = #{department.id}, " +
            "WHERE id = #{id}")
    void update(Staff staff);

    @Delete("DELETE FROM staff WHERE id = #{id}")
    void delete(int id);
}
