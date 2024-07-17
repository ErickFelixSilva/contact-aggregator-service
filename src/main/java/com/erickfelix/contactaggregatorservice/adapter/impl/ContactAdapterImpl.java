package com.erickfelix.contactaggregatorservice.adapter.impl;

import com.erickfelix.contactaggregatorservice.adapter.ContactAdapter;
import com.erickfelix.contactaggregatorservice.client.KenectLabsClient;
import com.erickfelix.contactaggregatorservice.domain.Contact;
import com.erickfelix.contactaggregatorservice.external.ContactMapper;
import com.erickfelix.contactaggregatorservice.external.KenectLabsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactAdapterImpl implements ContactAdapter {

    private final KenectLabsClient kenectLabsClient;

    public ContactAdapterImpl(KenectLabsClient kenectLabsClient) {
        this.kenectLabsClient = kenectLabsClient;
    }

    @Override
    public List<Contact> fetchAllContacts() {
        List<Contact> allContacts = new ArrayList<>();
        int page = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            ResponseEntity<KenectLabsResponse> response = kenectLabsClient.getContacts(page);
            KenectLabsResponse responseBody = response.getBody();
            if (responseBody != null && responseBody.getContacts() != null) {
                var contacts = ContactMapper.toInternal(responseBody.getContacts());
                allContacts.addAll(contacts);
            }

            HttpHeaders headers = response.getHeaders();
            Optional<String> currentPageHeader = Optional.ofNullable(headers.getFirst("Current-Page"));
            Optional<String> totalPagesHeader = Optional.ofNullable(headers.getFirst("Total-Pages"));

            if (currentPageHeader.isPresent() && totalPagesHeader.isPresent()) {
                int currentPage = Integer.parseInt(currentPageHeader.get());
                int totalPages = Integer.parseInt(totalPagesHeader.get());

                hasMorePages = currentPage < totalPages;
                page++;
            } else {
                hasMorePages = false;
            }
        }

        return allContacts;
    }
}
