package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
record IncomingPrice(
    int value,
    String currency
) {
}
