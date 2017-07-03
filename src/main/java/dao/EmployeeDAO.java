package dao;

import entity.Address;
import entity.Employee;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public interface EmployeeDAO {
    void add(Employee address) throws SQLException;;
    List<Employee> getAll() throws SQLException;;
    Employee getById(Long id) throws SQLException;;
    void update(Employee address) throws SQLException;;
    void remove(Employee address) throws SQLException;;
}
