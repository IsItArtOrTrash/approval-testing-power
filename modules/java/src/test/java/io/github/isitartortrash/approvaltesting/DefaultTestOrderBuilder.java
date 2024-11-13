package io.github.isitartortrash.approvaltesting;

import io.github.isitartortrash.approvaltesting.livecoding.ShopOrder;

import java.time.LocalDate;
import java.util.List;

import static io.github.isitartortrash.approvaltesting.livecoding.AddressBuilder.anAddress;
import static io.github.isitartortrash.approvaltesting.livecoding.CouponBuilder.aCoupon;
import static io.github.isitartortrash.approvaltesting.livecoding.CustomerBuilder.aCustomer;
import static io.github.isitartortrash.approvaltesting.livecoding.ItemBuilder.anItem;
import static io.github.isitartortrash.approvaltesting.livecoding.OrderBuilder.anOrder;
import static io.github.isitartortrash.approvaltesting.livecoding.PriceBuilder.aPrice;

public class DefaultTestOrderBuilder {
  public static ShopOrder aDefaultOrder(String orderId) {
    return anOrder()
        .id(orderId)
        .version(1)
        .items(
            List.of(
                anItem()
                    .id("someItemId")
                    .name("ATD 3 Conf. Days")
                    .amount(2)
                    .price(
                        aPrice()
                            .value(225000)
                            .monetaryUnit("cent")
                            .currency("EUR")
                            .build()
                    )
                    .build()
            )
        )
        .coupons(
            List.of(
                aCoupon()
                    .id("someCouponId")
                    .description("Speaker Coupon")
                    .reducedRateInPercentage(100)
                    .build()
            )
        )
        .deliveryDate(LocalDate.of(2024, 11, 22))
        .customer(
            aCustomer()
                .id("someCustomerId")
                .firstName("REWE")
                .lastName("Digital")
                .build()
        )
        .shippingAddress(
            anAddress()
                .id("someShippingAddressId")
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
            anAddress()
                .id("someBillingAddressId")
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
