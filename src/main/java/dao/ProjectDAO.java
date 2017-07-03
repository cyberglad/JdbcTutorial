package dao;

import entity.Employee;
import entity.Project;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public interface ProjectDAO {
    void add(Project address) throws SQLException;
    List<Project> getAll() throws SQLException;;
    Project getById(Long id) throws SQLException;;
    void update(Project address) throws SQLException;;
    void remove(Project address) throws SQLException;;
}
