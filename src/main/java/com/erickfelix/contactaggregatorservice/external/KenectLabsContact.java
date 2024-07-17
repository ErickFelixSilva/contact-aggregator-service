package com.erickfelix.contactaggregatorservice.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record KenectLabsContact (
        Long id,
        String name,
        String email,
        @JsonProperty("created_at") LocalDateTime createdAt,
        @JsonProperty("updated_at") LocalDateTime updatedAt) {}
