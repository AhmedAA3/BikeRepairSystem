package MVCsem2.integration.register;

import MVCsem2.model.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {

        registry = new CustomerRegistry();
    }

    @Test
    public void registeredCustomerShouldBeFoundByRegisteredPhoneNumber() {

        String registeredCustomerPhone = "0737654321";

        Customer registeredCustomer = registry.findCustomer(registeredCustomerPhone);

        assertNotNull(registeredCustomer,
                "Cristiano Ronaldo should exist in the hardcoded customer registry.");
        assertEquals("Cristiano Ronaldo", registeredCustomer.getName(),
                "The registry should return Cristiano Ronaldo for this phone number.");
        assertEquals("CR7@mail.com", registeredCustomer.getEmail(),
                "Cristiano Ronaldo's email should match the hardcoded customer data.");
        assertEquals(registeredCustomerPhone, registeredCustomer.getPhoneNumber(),
                "Cristiano Ronaldo's phone number should match the search value.");
    }

    @Test
    public void registeredCustomerShouldHaveExpectedBikeInformation() {

        String registeredCustomerPhone = "0737654321";

        Customer registeredCustomer = registry.findCustomer(registeredCustomerPhone);

        assertNotNull(registeredCustomer,
                "Cristiano Ronaldo should be found before checking bike information.");
        assertNotNull(registeredCustomer.getBike(),
                "Cristiano Ronaldo should have a registered bike.");
        assertEquals("Scotter", registeredCustomer.getBike().getBrand(),
                "The registered bike brand should match the hardcoded data.");
        assertEquals("SET401", registeredCustomer.getBike().getSerialNumber(),
                "The registered bike serial number should match the hardcoded data.");
    }
}