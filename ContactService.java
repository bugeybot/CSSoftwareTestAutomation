package org.snhu.cs320.contact;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.snhu.cs320.validation.Validation;

public class ContactService {
    
    static Map<String, Contact> CONTACT_DATABASE = new ConcurrentHashMap<>();
    
    private ContactService() {}
    
    public static boolean add(Contact contact) {
        if (CONTACT_DATABASE.containsKey(contact.getId())) return false;
        CONTACT_DATABASE.putIfAbsent(contact.getId(), contact);
        return true;
    }
    
    public static boolean delete(String id) {
        return CONTACT_DATABASE.remove(id) != null;
    }
    
    public static boolean update(String id, String newFirstName, String newLastName, String newPhone, String newAddress) {
        Contact existing = CONTACT_DATABASE.get(id);
        
        if (existing == null) return false;
        
        // Validate new values before updating
        Validation.validateNotBlank(newFirstName, "firstName");
        Validation.validateLength(newFirstName, "firstName", 1, 10);
        
        Validation.validateNotBlank(newLastName, "lastName");
        Validation.validateLength(newLastName, "lastName", 1, 10);
        
        Validation.validateNotBlank(newPhone, "phone");
        Validation.validateLength(newPhone, "phone", 10, 10);
        Validation.validateNumeric(newPhone, "phone");
        
        Validation.validateNotBlank(newAddress, "address");
        Validation.validateLength(newAddress, "address", 1, 30);
        
        // Apply updates if all validations pass
        existing.setFirstName(newFirstName);
        existing.setLastName(newLastName);
        existing.setPhone(newPhone);
        existing.setAddress(newAddress);
        
        return true;
    }
    
    public static Contact getContactById(String id) {
        return CONTACT_DATABASE.get(id);
    }
}
