package com.erickfelix.contactaggregatorservice.adapter.impl;

import com.erickfelix.contactaggregatorservice.client.KenectLabsClient;
import com.erickfelix.contactaggregatorservice.external.KenectLabsContact;
import com.erickfelix.contactaggregatorservice.domain.Contact;
import com.erickfelix.contactaggregatorservice.external.KenectLabsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class ContactAdapterImplTest {

    @Mock
    private KenectLabsClient kenectLabsClient;

    @InjectMocks
    private ContactAdapterImpl contactAdapter;

    private AutoCloseable closeable;

    private static final String TOKEN = "Bearer J7ybt6jv6pdJ4gyQP9gNonsY";

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        contactAdapter = new ContactAdapterImpl(kenectLabsClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testFetchAllContacts() {
        KenectLabsContact contact1 = new KenectLabsContact(1L, "jmadsen", "jmadsen@kenect.com", LocalDateTime.now(), LocalDateTime.now());
        KenectLabsContact contact2 = new KenectLabsContact(2L, "jsmith", "jsmith@kenect.com", LocalDateTime.now(), LocalDateTime.now());
        List<KenectLabsContact> contacts = Arrays.asList(contact1, contact2);

        KenectLabsResponse response = new KenectLabsResponse();
        response.setContacts(contacts);

        ResponseEntity<KenectLabsResponse> responseEntity = ResponseEntity.ok()
                .header("Current-Page", "1")
                .header("Total-Pages", "1")
                .body(response);

        when(kenectLabsClient.getContacts(anyInt())).thenReturn(responseEntity);

        List<Contact> result = contactAdapter.fetchAllContacts();

        assertEquals(2, result.size());
        verify(kenectLabsClient, times(1)).getContacts(anyInt());
    }
}
