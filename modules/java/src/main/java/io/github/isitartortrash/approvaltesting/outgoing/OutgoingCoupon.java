package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

@Builder
public record OutgoingCoupon(
    String id,
    String description,
    int reducedRateInPercentage
) {
}
