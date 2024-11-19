package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Builder
public record OutgoingOrder(
    String id,
    Long version,
    List<OutgoingItem> items,
    List<OutgoingCoupon> coupons,
    Instant orderTimeStamp,
    LocalDate deliveryDate,
    List<OutgoingPrice> shippingCost,
    OutgoingCustomer customer,
    OutgoingAddress shippingAddress,
    OutgoingAddress billingAddress) {
}
