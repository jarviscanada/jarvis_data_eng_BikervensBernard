package ca.jrvs.apps.practice;

import ca.jrvs.apps.jdbc.Customer;
import ca.jrvs.apps.jdbc.CustomerDAO;
import ca.jrvs.apps.jdbc.DbConnectionManager;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CustomerDAOTest {
    static CustomerDAO tester = null;

    static DbConnectionManager dbConnectionManager =
    new DbConnectionManager(IConnection.HOST, IConnection.DB, IConnection.USER, IConnection.PASSWORD, IConnection.PORT);

    static boolean connected = false;

    @BeforeClass
    public static void test_CustomerDAO_setup() {
        if (!connected) {
            try {
                Connection connection = dbConnectionManager.getConnection();
                tester = new CustomerDAO(connection);
                connected = true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void test_CustomerDAO_Create() {
        Customer c = new Customer();
        c.setFirstName("Morty");
        c.setLastName("Sanchez");
        c.setEmail("rick@adult.swim.com");
        c.setPhone("(555) 555-5555");
        c.setAddress("90210 beverly hills");
        c.setCity("Los Angeles");
        c.setState("California");
        c.setZipCode("90210");
        Customer out = tester.create(c);
        assertTrue(out!=null);
        assertTrue(tester.findById(out.getId()).getEmail().equals(c.getEmail()));
    }

    public void test_CustomerDAO_Read() {
        Stream<Customer> out = tester.findAll().stream().filter(i->i.getFirstName().equals("Morty"));
        assertTrue(out.count() == 1);
    }

    public void test_CustomerDAO_Update() {
        Customer out = tester.findById(10000);
        out.setFirstName("Morty&rick");
        tester.update(out);
        Stream<Customer> l = tester.findAll().stream().filter(i->i.getFirstName().equals("Morty&rick"));
        assertTrue(l.count() == 1);
    }

    public void test_CustomerDAO_Delete() {
        Customer c = tester.findById(10000);
        assertTrue(c != null);
        assertTrue(c.getEmail().equals("rick@adult.swim.com"));

        tester.delete(10000);
        Stream<Customer> l = tester.findAll().stream().filter(i->i.getFirstName().equals("Morty&rick") || i.getFirstName().equals("Morty"));
        assertTrue(l.count() == 0);
    }
}
