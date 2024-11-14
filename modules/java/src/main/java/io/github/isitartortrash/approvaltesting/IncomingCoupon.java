package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

@Builder
record IncomingCoupon(
    String id,
    String description
) { }
