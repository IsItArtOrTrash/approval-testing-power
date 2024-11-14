package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
record IncomingItem(
    String id,
    String name,
    int amount,
    IncomingPrice price
) {
}
