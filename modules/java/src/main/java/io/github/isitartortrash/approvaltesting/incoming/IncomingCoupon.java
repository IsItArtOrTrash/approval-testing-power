package io.github.isitartortrash.approvaltesting.incoming;

import lombok.Builder;

@Builder
public record IncomingCoupon(
    String id,
    String description
) {
}
