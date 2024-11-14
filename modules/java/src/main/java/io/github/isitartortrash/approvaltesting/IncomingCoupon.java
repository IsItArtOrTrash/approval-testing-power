package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
public record IncomingCoupon(
    String id,
    String description
) { }
