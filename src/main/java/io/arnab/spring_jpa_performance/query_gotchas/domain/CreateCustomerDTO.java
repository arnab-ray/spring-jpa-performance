package io.arnab.spring_jpa_performance.query_gotchas.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCustomerDTO(String firstName, String lastName, String mobileNumber) {
}
