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
import static org.junit.Assert.*;

public class CustomerDAOTest {
    static CustomerDAO tester = null;
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    static DbConnectionManager dbConnectionManager = new DbConnectionManager(
            "localhost",
            "postgres",
            "postgres",
            "docker",
            "5432"
    );

    @BeforeClass
    public static void test_CustomerDAO_setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        try {
            Connection connection = dbConnectionManager.getConnection();
            tester = new CustomerDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test_CustomerDAO_Create() {
        Customer c = new Customer();
        c.setFirstName("Rick");
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

    }

    public void test_CustomerDAO_Update() {

    }

    public void test_CustomerDAO_Delete() {

    }
}
