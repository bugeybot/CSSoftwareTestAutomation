package org.snhu.cs320.contact;

import org.snhu.cs320.validation.Validation;

public class Contact {

    private final String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String id, String firstName, String lastName, String phone, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;

        validate();
    }

    public void validate() {
        Validation.validateNotBlank(id, "id");
        Validation.validateLength(id, "id", 1, 10);

        Validation.validateNotBlank(firstName, "firstName");
        Validation.validateLength(firstName, "firstName", 1, 10);

        Validation.validateNotBlank(lastName, "lastName");
        Validation.validateLength(lastName, "lastName", 1, 10);

        Validation.validateNotBlank(phone, "phone");
        Validation.validateLength(phone, "phone", 10, 10);
        Validation.validateNumeric(phone, "phone");

        Validation.validateNotBlank(address, "address");
        Validation.validateLength(address, "address", 1, 30);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        validate();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        validate();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        validate();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        validate();
    }

    public String getId() {
        return id;
    }
}
