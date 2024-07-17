package com.erickfelix.contactaggregatorservice.adapter.impl;

import com.erickfelix.contactaggregatorservice.adapter.ContactAdapter;
import com.erickfelix.contactaggregatorservice.client.KenectLabsClient;
import com.erickfelix.contactaggregatorservice.domain.Contact;
import com.erickfelix.contactaggregatorservice.external.KenectLabsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
//                allContacts.addAll(responseBody.getContacts());
            }

            HttpHeaders headers = response.getHeaders();
            if (!headers.isEmpty()) {
                int currentPage = Integer.parseInt(headers.get("Current-Page").getFirst());
                int totalPages = Integer.parseInt(headers.get("Total-Pages").getFirst());

                hasMorePages = currentPage < totalPages;
                page++;
            } else {
                hasMorePages = false;
            }
        }

        return Collections.emptyList();
    }
}
