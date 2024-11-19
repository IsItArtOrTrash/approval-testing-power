package io.github.isitartortrash.approvaltesting.incoming;

import lombok.Builder;

@Builder
public record IncomingAddress(
    String firstName,
    String lastName,
    String streetName,
    String houseNumber,
    String city,
    String country,
    String phone,
    String email,
    String postalCode) {
}
