package io.github.isitartortrash.approvaltesting;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record IncomingOrder(
    String id,
    List<IncomingItem> items,
    List<IncomingCoupon> coupons,
    LocalDate deliveryDate,
    UUID customerUuid,
    IncomingAddress shippingAddress,
    IncomingAddress billingAddress) {
}
