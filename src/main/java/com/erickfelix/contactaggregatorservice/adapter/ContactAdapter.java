package com.erickfelix.contactaggregatorservice.adapter;

import com.erickfelix.contactaggregatorservice.domain.Contact;

import java.util.List;

public interface ContactAdapter {
    List<Contact> fetchAllContacts();
}