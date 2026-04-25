package MVCsem2.integration.register;

import MVCsem2.model.entity.Bike;
import MVCsem2.model.entity.Customer;

/**
 *  This class handles stored customer data 
 */
public class CustomerRegistry {
    private final Customer[] customers;

    /**
     * Creates a customer registry with hardcoded data cause we dont have a database
     */
    public CustomerRegistry() {
        customers = new Customer[] {
            new Customer("Ahmed Adam", "ahmed@mail.com", "0701234567",
                    new Bike("Abdurraham", "Reaction Hybrid", "CUBE123")),
            new Customer("Sara Svensson", "sara@mail.com", "0737654321",
                    new Bike("Trek", "Powerfly 5", "TREK456"))
        };
    }

    /**
     * Uses a customers phone number as a parameter for look up in the hardcoded database.
     * @param phoneNumber The phone number used to identify the customer.
     * @return The customer with the given phone number, null if the customer was not found.
     */
    public Customer findCustomer(String phoneNumber) {
       for (int i = 0; i < customers.length; i++) {
    		if (customers[i].getPhoneNumber().equals(phoneNumber)) {
        		return customers[i];
    		}
		}
        return null;
    }
}
