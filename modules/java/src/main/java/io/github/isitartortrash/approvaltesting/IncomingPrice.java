package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
public record IncomingPrice(
    int value,
    Currency currency
) {
}
