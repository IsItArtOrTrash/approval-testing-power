package io.github.isitartortrash.approvaltesting.outgoing;

import io.github.isitartortrash.approvaltesting.incoming.Currency;
import lombok.Builder;

@Builder
public record OutgoingPrice(
    int value,
    String monetaryUnit,
    String currency
) {
}
