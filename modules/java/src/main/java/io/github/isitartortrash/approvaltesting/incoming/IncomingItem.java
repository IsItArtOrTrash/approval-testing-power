package io.github.isitartortrash.approvaltesting.incoming;

import lombok.Builder;

@Builder
public record IncomingItem(
    String id,
    String name,
    int amount,
    IncomingPrice price
) {
}
