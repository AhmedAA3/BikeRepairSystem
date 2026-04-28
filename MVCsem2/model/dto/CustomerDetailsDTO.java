package MVCsem2.model.dto;

import MVCsem2.model.entity.Customer;

/**
 * Innehåller detaljer om en kund. Detta är en immutable databärare.
 */
public class CustomerDetailsDTO {
    private final String name;
    private final String email;
    private final String phone;

    /**
     * Skapar en ny instans baserat på en kund-entitet.
     * @param customer Kunden som data ska hämtas ifrån.
     */
    public CustomerDetailsDTO(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhoneNumber();
    }


    /**
     * Returns the customers name.
     */
    public String getName() { return name; }


    /**
     * Returns the customers email.
     */
    public String getEmail() { return email; }



    /**
     * Returns the customers phone number.
     */
    public String getPhone() { return phone; }
}
