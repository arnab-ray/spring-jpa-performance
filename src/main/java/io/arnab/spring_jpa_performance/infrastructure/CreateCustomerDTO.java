package io.arnab.spring_jpa_performance.infrastructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateCustomerDTO(String firstName, String lastName, String mobileNumber) {
}
