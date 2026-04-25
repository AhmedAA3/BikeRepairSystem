package se.kth.iv1350.bikerepair.model.dto;

import se.kth.iv1350.bikerepair.model.entity.Customer;

/**
 * Contains details about a customer. This is an immutable data carrier 
 * used to pass data between layers.
 */
public class CustomerDetailsDTO {
    private final String name;
    private final String email;
    private final String phone;

    /**
     * Creates a new instance representing the specified customer.
     * * @param customer The customer whose details are to be encapsulated.
     */
    public CustomerDetailsDTO(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

    /**
     * Returns the customer's name.
     * * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's email address.
     * * @return The email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer's phone number.
     * * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }
}
