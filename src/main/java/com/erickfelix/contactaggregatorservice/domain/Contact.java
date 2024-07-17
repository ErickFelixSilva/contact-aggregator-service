package com.erickfelix.contactaggregatorservice.domain;

public record Contact(Long id, String name, String email, String source, String createdAt, String updatedAt) {
}
