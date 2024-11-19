package io.github.isitartortrash.approvaltesting.outgoing;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OutgoingOrder(
    String id,
    Long version,
    List<OutgoingItem> items,
    List<OutgoingCoupon> coupons,
    LocalDateTime orderTimeStamp,
    LocalDate deliveryDate,
    List<OutgoingPrice> shippingCost,
    OutgoingCustomer customer,
    OutgoingAddress shippingAddress,
    OutgoingAddress billingAddress) {
}
