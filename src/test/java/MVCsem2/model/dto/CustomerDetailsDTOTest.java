package MVCsem2.model.dto;



import MVCsem2.model.entity.Bike;
import MVCsem2.model.entity.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDetailsDTOTest {

    @Test
    public void testCustomerDetailsDTOCopiesCristianoRonaldoData() {
        Customer customer = new Customer("Cristiano Ronaldo", "CR7@mail.com", "0737654321",
                new Bike("Scotter", " 1", "SET401"));

        CustomerDetailsDTO result = new CustomerDetailsDTO(customer);

        assertEquals("Cristiano Ronaldo", result.getName(),
                "DTO should copy customer name.");
        assertEquals("CR7@mail.com", result.getEmail(),
                "DTO should copy customer email.");
        assertEquals("0737654321", result.getPhone(),
                "DTO should copy customer phone.");
    }
}