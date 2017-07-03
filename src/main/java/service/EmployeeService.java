package service;

import bl.Util;
import dao.EmployeeDAO;
import entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vserdiuk on 2/5/17.
 */
public class EmployeeService extends Util implements EmployeeDAO {

    private Connection connection = getConnection();


    public void add(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE] (ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) " +
                "VALUES(?, ?, ?, ?, ?)";

        try {
            Long maxID = getMaxID();
            maxID = maxID + 1L;
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, maxID);
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setDate(4, employee.getBirthday());
            preparedStatement.setLong(5, employee.getAddressId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }


    public List<Employee> getAll() throws SQLException {
        List<Employee> employeeList = new ArrayList<Employee>();

        String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE] ";

        Statement statement = null;
        try {
            if(connection.isClosed())
                connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("ID"));
                employee.setFirstName(resultSet.getString("FIRST_NAME"));
                employee.setLastName(resultSet.getString("LAST_NAME"));
                employee.setBirthday(resultSet.getDate("BIRTHDAY"));
                employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeList;
    }


    public Employee getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE]  WHERE ID=?";

        Employee employee = new Employee();
        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            employee.setId(resultSet.getLong("ID"));
            employee.setFirstName(resultSet.getString("FIRST_NAME"));
            employee.setLastName(resultSet.getString("LAST_NAME"));
            employee.setBirthday(resultSet.getDate("BIRTHDAY"));
            employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employee;
    }

    public void update(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE]  SET FIRST_NAME=?, LAST_NAME=?, BIRTHDAY=?, ADDRESS_ID=0 WHERE ID=0";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setDate(3, employee.getBirthday());
            preparedStatement.setLong(4, employee.getAddressId());
            preparedStatement.setLong(5, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }


    public void remove(Employee employee) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE]   WHERE ID=?";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public Long getMaxID() throws SQLException {
        Long maxId = 0L;

        String sql = "SELECT max(ID) as MAX_ID  FROM [jcaps_mapping_Q].[dbo].[TB_EMPLOYEE]";

        Statement statement = null;
        try {
            if(connection.isClosed())
                connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                maxId = resultSet.getLong("MAX_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return maxId;
    }
}