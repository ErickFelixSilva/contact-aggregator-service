package com.erickfelix.contactaggregatorservice.service;

import com.erickfelix.contactaggregatorservice.adapter.ContactAdapter;
import com.erickfelix.contactaggregatorservice.domain.Contact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactAdapter contactAdapter;

    public ContactService(ContactAdapter contactAdapter) {
        this.contactAdapter = contactAdapter;
    }

    public List<Contact> getAllContacts() {
        return contactAdapter.fetchAllContacts();
    }
}
