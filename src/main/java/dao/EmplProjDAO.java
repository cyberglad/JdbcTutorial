package dao;

import entity.EmplProj;
import entity.Project;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public interface EmplProjDAO {
    void add(EmplProj emplProj) throws SQLException;;
    List<EmplProj> getAll() throws SQLException;;;
    EmplProj getByEmployeeIdAndProjectId(Long employeeId, Long projectId) throws SQLException;;;
    void update(EmplProj emplProj) throws SQLException;;;
    void remove(EmplProj emplProj) throws SQLException;;;
}
