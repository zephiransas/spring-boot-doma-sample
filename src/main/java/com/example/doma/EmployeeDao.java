package com.example.doma;

import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface EmployeeDao {

    @Select
    List<Employee> findAll();

    @Select
    Employee findById(int id);

    @Insert
    int insert(Employee employee);

    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee e);
}
