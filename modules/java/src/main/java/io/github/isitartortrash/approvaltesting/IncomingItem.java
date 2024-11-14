package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
public record IncomingItem(
    String id,
    String name,
    int amount,
    IncomingPrice price
) {
}
