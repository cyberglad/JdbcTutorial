package dao;

import entity.Address;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public interface AddressDAO {
    void add(Address address) throws SQLException;
    List<Address> getAll() throws SQLException;
    Address getById(Long id) throws SQLException;
    void update(Address address) throws SQLException;
    void remove(Address address) throws SQLException;
}
