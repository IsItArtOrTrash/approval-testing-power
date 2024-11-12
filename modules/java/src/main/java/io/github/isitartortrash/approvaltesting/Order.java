package io.github.isitartortrash.approvaltesting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

record Order(
    String id,
    int version,
    List<Item> items,
    List<Coupon> coupons,
    LocalDateTime orderTimeStamp,
    LocalDate deliveryDate,
    List<Price> shippingCost,
    Customer customer,
    Address shippingAddress,
    Address billingAddress) {
}
