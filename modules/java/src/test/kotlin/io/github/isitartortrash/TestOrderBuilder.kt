package io.github.isitartortrash

import io.github.isitartortrash.approvaltesting.AddressBuilder.Companion.anAddress
import io.github.isitartortrash.approvaltesting.CouponBuilder.Companion.aCoupon
import io.github.isitartortrash.approvaltesting.CustomerBuilder.Companion.aCustomer
import io.github.isitartortrash.approvaltesting.ItemBuilder.Companion.anItem
import io.github.isitartortrash.approvaltesting.OrderBuilder
import io.github.isitartortrash.approvaltesting.OrderBuilder.Companion.anOrder
import io.github.isitartortrash.approvaltesting.PriceBuilder.Companion.aPrice
import io.github.isitartortrash.approvaltesting.ShopOrder
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomStringUtils.randomNumeric
import java.time.LocalDate
import kotlin.random.Random.Default.nextInt

fun anyOrder(orderId: String): OrderBuilder {
    return anOrder()
        .id(orderId)
        .version(nextInt(50))
        .items(listOf(anyItem().build()))
        .coupons(listOf(anyCoupon().build()))
        .deliveryDate(LocalDate.of(2024, nextInt(1, 13), nextInt(1, 29)))
        .customer(anyCustomer().build())
        .shippingAddress(anyAddress().build())
        .billingAddress(anyAddress().build())
}

private fun anyItem() = anItem()
    .id(randomAlphanumeric(10))
    .name(randomAlphanumeric(10))
    .amount(nextInt(1000))
    .price(anyPrice().build())

private fun anyPrice() = aPrice()
    .value(nextInt(100, 1000000))
    .monetaryUnit(randomAlphanumeric(10))
    .currency(randomAlphanumeric(10))

private fun anyCoupon() = aCoupon()
    .id(randomAlphanumeric(10))
    .description(randomAlphanumeric(10))
    .reducedRateInPercentage(nextInt(1, 101))

private fun anyCustomer() = aCustomer()
    .id(randomAlphanumeric(10))
    .firstName(randomAlphanumeric(10))
    .lastName(randomAlphanumeric(10))

private fun anyAddress() = anAddress()
    .id(randomAlphanumeric(10))
    .firstName(randomAlphanumeric(10))
    .lastName(randomAlphanumeric(10))
    .streetName(randomAlphanumeric(10))
    .houseNumber(randomNumeric(2))
    .postalCode(randomNumeric(5))
    .city(randomAlphanumeric(10))
    .country(randomAlphanumeric(10))
    .phone(randomNumeric(11))
    .email(randomAlphanumeric(10))

fun aDefaultOrder(orderId: String?): ShopOrder {
    return anOrder()
        .id(orderId!!)
        .version(1)
        .items(
            listOf(
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
            listOf(
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
        .build()
}