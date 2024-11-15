package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

@Builder
public record OutgoingItem(
    String id,
    String name,
    int amount,
    OutgoingPrice price
) {
}
