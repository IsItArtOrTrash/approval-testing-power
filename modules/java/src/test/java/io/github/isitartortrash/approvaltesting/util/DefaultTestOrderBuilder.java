package io.github.isitartortrash.approvaltesting.util;

import io.github.isitartortrash.approvaltesting.incoming.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.isitartortrash.approvaltesting.incoming.Currency.EUR;

public class DefaultTestOrderBuilder {
  public static IncomingOrder aDefaultOrder(String orderId) {
    return IncomingOrder.builder()
        .id(orderId)
        .items(
            List.of(
                IncomingItem.builder()
                    .id("someItemId")
                    .name("ATD 3 Conf. Days")
                    .amount(2)
                    .price(
                        IncomingPrice.builder()
                            .value(225000)
                            .currency(EUR)
                            .build()
                    )
                    .build()
            )
        )
        .coupons(
            List.of(
                IncomingCoupon.builder()
                    .id("someCouponId")
                    .description("Speaker Coupon")
                    .build()
            )
        )
        .deliveryDate(LocalDate.of(2024, 11, 22))
        .customerUuid(UUID.fromString("9e71d9c1-a066-41e0-a79e-061089110d85"))
        .shippingAddress(
            IncomingAddress.builder()
                .firstName("Janina")
                .lastName("Nemec")
                .streetName("Schanzenstr.")
                .houseNumber("6-20")
                .postalCode("51063")
                .city("Köln")
                .country("Deutschland")
                .phone("0221 9758420")
                .email("kontakt@rewe-digital.com")
                .build()
        )
        .billingAddress(
            IncomingAddress.builder()
                .firstName("Micha")
                .lastName("Kutz")
                .streetName("Domstr.")
                .houseNumber("20")
                .postalCode("50668")
                .city("Köln")
                .country("Deutschland")
                .phone("+49 221 1490")
                .email("info@rewe-group.com")
                .build()
        )
        .build();
  }
}
