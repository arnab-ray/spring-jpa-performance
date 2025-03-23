package io.arnab.spring_jpa_performance.infrastructure;

import io.arnab.spring_jpa_performance.domain.ReservationState;

public record ReservationDTO(String id, ReservationState state) {
}
