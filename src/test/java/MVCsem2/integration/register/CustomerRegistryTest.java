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
    public void cristianoRonaldoShouldBeFoundByRegisteredPhoneNumber() {

        String cristianoPhone = "0737654321";

        Customer cristiano = registry.findCustomer(cristianoPhone);

        assertNotNull(cristiano,
                "Cristiano Ronaldo should exist in the hardcoded customer registry.");
        assertEquals("Cristiano Ronaldo", cristiano.getName(),
                "The registry should return Cristiano Ronaldo for this phone number.");
        assertEquals("CR7@mail.com", cristiano.getEmail(),
                "Cristiano Ronaldo's email should match the hardcoded customer data.");
        assertEquals(cristianoPhone, cristiano.getPhoneNumber(),
                "Cristiano Ronaldo's phone number should match the search value.");
    }

    @Test
    public void cristianoRonaldoShouldHaveExpectedBikeInformation() {

        String cristianoPhone = "0737654321";

        Customer cristiano = registry.findCustomer(cristianoPhone);

        assertNotNull(cristiano,
                "Cristiano Ronaldo should be found before checking bike information.");
        assertNotNull(cristiano.getBike(),
                "Cristiano Ronaldo should have a registered bike.");
        assertEquals("Scotter", cristiano.getBike().getBrand(),
                "The registered bike brand should match the hardcoded data.");
        assertEquals("SET401", cristiano.getBike().getSerialNumber(),
                "The registered bike serial number should match the hardcoded data.");
    }

    @Test
    public void unknownPhoneNumberShouldNotReturnCustomer() {
        
        Customer missingCustomer = registry.findCustomer("0000000000");

        assertNull(missingCustomer,
                "A phone number that is not registered should not return a customer.");
    }
}