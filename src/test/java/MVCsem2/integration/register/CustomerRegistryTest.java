package MVCsem2.integration.register;

import MVCsem2.model.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRegistryTest {
    private CustomerRegistry customerRegistry;

    @BeforeEach
    public void setUp() {
        customerRegistry = new CustomerRegistry();
    }

    @Test
    public void testFindCristianoRonaldoByPhoneNumber() {
        Customer result = customerRegistry.findCustomer("0737654321");

        assertNotNull(result, "Cristiano Ronaldo should be found.");
        assertEquals("Cristiano Ronaldo", result.getName(),
                "Wrong customer was found.");
        assertEquals("CR7@mail.com", result.getEmail(),
                "Wrong email was returned.");
        assertEquals("0737654321", result.getPhoneNumber(),
                "Wrong phone number was returned.");
    }

    @Test
    public void testCristianoRonaldoBikeDataExists() {
        Customer result = customerRegistry.findCustomer("0737654321");

        assertNotNull(result.getBike(), "Cristiano Ronaldo should have a bike.");
        assertEquals("Scotter", result.getBike().getBrand(),
                "Wrong bike brand was returned.");
        assertEquals("SET401", result.getBike().getSerialNumber(),
                "Wrong bike serial number was returned.");
    }

    @Test
    public void testFindCustomerThatDoesNotExist() {
        Customer result = customerRegistry.findCustomer("0000000000");

        assertNull(result, "A customer that does not exist should return null.");
    }
}