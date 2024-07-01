package org.snhu.cs320.contact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.snhu.cs320.exceptions.ValidationException;

class ContactServiceTest {
    
    @BeforeEach
    void init() {
        ContactService.CONTACT_DATABASE.clear();
    }

    @Test
    void addSuccess() {
        Contact contact = new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane");
        ContactService.add(contact);
        assertThat(ContactService.CONTACT_DATABASE)
            .containsEntry("12345", contact);
    }
    
    @Test
    void delete() {
        ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
        ContactService.delete("12345");
        assertThat(ContactService.CONTACT_DATABASE)
            .doesNotContainKey("12345");
    }
    
    @Test
    void updateSuccess() {
        ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
        
        boolean updated = ContactService.update("12345", "NewFirst", "NewLast", "2229995555", "4321 Pine Street");
        assertThat(updated).isTrue();
        
        Contact updatedContact = ContactService.getContactById("12345");
        assertThat(updatedContact).isNotNull();
        assertThat(updatedContact.getFirstName()).isEqualTo("NewFirst");
        assertThat(updatedContact.getLastName()).isEqualTo("NewLast");
        assertThat(updatedContact.getPhone()).isEqualTo("2229995555");
        assertThat(updatedContact.getAddress()).isEqualTo("4321 Pine Street");
    }
    
    @Test
    void updateFail() {
        ContactService.add(new Contact("12345", "First", "Last", "5553334444", "1234 Loblolly Lane"));
        
        // Attempting to update with invalid phone (contains letters)
        assertThatThrownBy(() -> ContactService.update("12345", "NewFirst", "NewLast", "222999555A", "4321 Pine Street"))
            .isInstanceOf(ValidationException.class);
        
        // Attempting to update a non-existent contact
        boolean updated = ContactService.update("67890", "NewFirst", "NewLast", "2229995555", "4321 Pine Street");
        assertThat(updated).isFalse();
    }
}
