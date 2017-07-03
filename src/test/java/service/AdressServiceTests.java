package service;

import entity.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by glady on 30.06.2017.
 */
public class AdressServiceTests {
    private static final Logger LOG = LoggerFactory.getLogger(AdressServiceTests.class);


    @Test
    public void testGetAll() throws SQLException {
        LOG.info("************ testGetAll**************");
        AddressService addressService = new AddressService();
        List<Address> addressList = addressService.getAll();
        for(Address address: addressList)
        {
            LOG.info(address.getId()+" - "+address.getCity()+" - "+address.getCountry()+" - "+address.getPostCode()+" - "+address.getStreet());
        }

    }
    @Test
    public void testAdd() throws SQLException {
        LOG.info("************ testAdd**************");

        AddressService addressService = new AddressService();
        Long maxID = addressService.getMaxID();
        maxID = maxID + 1L;
        Address address = new Address();
        address.setId(maxID);
        address.setCity("Moscow");
        address.setCountry("Russia");
        address.setPostCode("123456");
        address.setStreet("Aviatsionnaya 13");
        addressService.add(address);

    }
}
