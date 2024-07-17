package com.erickfelix.contactaggregatorservice.external;

import com.erickfelix.contactaggregatorservice.domain.Contact;

import java.util.List;

public class ContactMapper {

    private ContactMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static List<Contact> toInternal(List<KenectLabsContact> externalContacts) {
        return externalContacts.stream()
                .map(ContactMapper::toInternal)
                .toList();
    }

    public static Contact toInternal(KenectLabsContact externalContact) {
        return new Contact(
                externalContact.id(),
                externalContact.name(),
                externalContact.email(),
                "KENECT_LABS",
                externalContact.createdAt().toString(),
                externalContact.updatedAt().toString()
        );
    }
}
