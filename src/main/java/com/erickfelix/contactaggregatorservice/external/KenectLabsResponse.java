package com.erickfelix.contactaggregatorservice.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KenectLabsResponse {
    @JsonProperty("contacts")
    private List<KenectLabsContact> contacts;

    public List<KenectLabsContact> getContacts() {
        return contacts;
    }

    public void setContacts(List<KenectLabsContact> contacts) {
        this.contacts = contacts;
    }
}
