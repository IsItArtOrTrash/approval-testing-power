package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

@Builder
public record OutgoingPrice(
    int value,
    String monetaryUnit,
    String currency
) {
}
