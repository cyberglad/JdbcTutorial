package service;
import entity.*;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
/**
 * Created by glady on 30.06.2017.
 */
public class AllTests {
    private static final Logger LOG = LoggerFactory.getLogger(AllTests.class);

    AddressService addressService ;
    EmployeeService employeeService ;
    ProjectService projectService ;
    EmplProjService emplProjService ;

    @Before
    public void setup()
    {
         addressService = new AddressService();
         employeeService = new EmployeeService();
         projectService = new ProjectService();
         emplProjService = new EmplProjService();
    }
    @Test
    public void testAll() throws SQLException {


        Address address = new Address();
        address.setId(1L);
        address.setCountry("DC");
        address.setCity("Gotham City");
        address.setStreet("Arkham street 1");
        address.setPostCode("12345");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939, Calendar.MAY, 1);

        employee.setBirthday(new java.sql.Date(calendar.getTime().getTime()));
        employee.setAddressId(address.getId());

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Gotham City Police Department Commissioner");

        EmplProj emplProj = new EmplProj();
        emplProj.setEmployeeId(employee.getId());
        emplProj.setProjectId(project.getId());

        try {
            addressService.add(address);
            employeeService.add(employee);
            projectService.add(project);
            emplProjService.add(emplProj);

            List<Address> addressList = addressService.getAll();
            List<Employee> employeeList = employeeService.getAll();
            LOG.info("Here are listed employees");
            for (Employee e : employeeList) {
                LOG.info(e.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAll() throws SQLException {
        try {


            List<Address> addressList = addressService.getAll();
            LOG.info("Here are listed addresses");
            for (Address e : addressList) {
                LOG.info(e.toString());
            }
            List<Employee> employeeList = employeeService.getAll();
            LOG.info("Here are listed employees");
            for (Employee e : employeeList) {
                LOG.info(e.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

