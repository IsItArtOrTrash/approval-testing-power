package io.github.isitartortrash.approvaltesting.incoming;

import lombok.Builder;

@Builder
public record IncomingPrice(
    int value,
    Currency currency
) {
}
