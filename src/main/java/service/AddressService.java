package service;

import bl.Util;
import dao.AddressDAO;
import entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public class AddressService extends Util implements AddressDAO{

    Connection connection = getConnection();

    public void add(Address address) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO [jcaps_mapping_Q].[dbo].[TB_ADDRESS](ID, COUNTRY, CITY, STREET, POST_CODE) VALUES (?,?,?,?,?)";
        try
        {

            Long maxID = getMaxID();
            maxID = maxID + 1L;
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, maxID);
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostCode());

            preparedStatement.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    public List<Address> getAll() throws SQLException {
        List<Address> addressList = new ArrayList<Address>();

        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM [jcaps_mapping_Q].[dbo].[TB_ADDRESS]";

        Statement statement = null;
        try {
            if(connection.isClosed())
                connection = getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostCode(resultSet.getString("POST_CODE"));

                addressList.add(address);
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
        return addressList;
    }


    public Address getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM [jcaps_mapping_Q].[dbo].[TB_ADDRESS] WHERE ID=?";

        Address address = new Address();
        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            address.setId(resultSet.getLong("ID"));
            address.setCountry(resultSet.getString("COUNTRY"));
            address.setCity(resultSet.getString("CITY"));
            address.setStreet(resultSet.getString("STREET"));
            address.setPostCode(resultSet.getString("POST_CODE"));

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
        return address;
    }


    public void update(Address address) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE [jcaps_mapping_Q].[dbo].[TB_ADDRESS] SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, address.getCountry());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setString(4, address.getPostCode());
            preparedStatement.setLong(5, address.getId());

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

    public void remove(Address address) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM [jcaps_mapping_Q].[dbo].[TB_ADDRESS] WHERE ID=?";

        try {
            if(connection.isClosed())
                connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, address.getId());

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

        String sql = "SELECT max(ID) as MAX_ID  FROM [jcaps_mapping_Q].[dbo].[TB_ADDRESS]";

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
