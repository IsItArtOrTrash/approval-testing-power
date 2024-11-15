package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

@Builder
public record OutgoingAddress(
    String id,
    String firstName,
    String lastName,
    String streetName,
    String houseNumber,
    String city,
    String country,
    String latitude,
    String longitude,
    String phone,
    String email,
    String postalCode,
    CustomerStatus status
) {
}
