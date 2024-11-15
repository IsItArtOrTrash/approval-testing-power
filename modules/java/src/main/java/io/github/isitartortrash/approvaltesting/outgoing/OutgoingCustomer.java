package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

@Builder
public record OutgoingCustomer(
    String id,
    String firstName,
    String lastName
) {
}
