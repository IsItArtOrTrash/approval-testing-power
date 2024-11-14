package io.github.isitartortrash.approvaltesting.livecoding

import io.github.isitartortrash.approvaltesting.Currency.EUR
import java.time.LocalDateTime
import java.util.UUID

fun ShopAddress.enrich(
    latitude: String,
    longitude: String,
    status: CustomerStatus,
    id: String = UUID.randomUUID().toString()
) = AddressResult(
    id = id,
    firstName = firstName,
    lastName = lastName,
    streetName = streetName,
    houseNumber = houseNumber,
    city = city,
    country = country,
    phone = phone,
    latitude = latitude,
    longitude = longitude,
    email = email,
    postalCode = postalCode,
    status = status,
)

fun enrichCustomer(id: UUID): CustomerResult {
    if (id == UUID.fromString( "9e71d9c1-a066-41e0-a79e-061089110d85")) {
        return CustomerResult(id = id.toString(), firstName = "REWE", lastName = "Digital")
    }
    return CustomerResult(
        id = id.toString(),
        firstName = "UNKNOWN",
        lastName = "UNKNOWN",
    )
}

fun ShopPrice.enrich() = PriceResult(
    value = value,
    monetaryUnit = currency.getMonetaryUnit(),
    currency = currency.name
)

fun ShopItem.enrich() = ItemResult(
    id = id,
    name = name,
    amount = amount,
    price = price.enrich(),
)

fun ShopCoupon.enrich() = CouponResult(
    id = id,
    description = description,
    reducedRateInPercentage = if (id == "speakerCouponId") 100 else 10
)

fun ShopOrder.enrich() = OrderResult(
    id = id,
    version = 1L,
    items = items.map { it.enrich() },
    coupons = coupons.map { it.enrich() },
    orderTimeStamp = LocalDateTime.now(),
    deliveryDate = deliveryDate,
    shippingCost = listOf(
        PriceResult(
            value = 500,
            monetaryUnit = EUR.monetaryUnit,
            currency = EUR.name
        )
    ),
    customer = enrichCustomer(customerUuid),
    shippingAddress = shippingAddress.enrich(
        latitude = "50.96490882194811",
        longitude = "7.014472855463499",
        status = CustomerStatus.KNOWN_CUSTOMER,
        id = UUID.fromString("1fbbb9b4-dd34-4930-b54e-d896a68ba343").toString()
    ),
    billingAddress = billingAddress.enrich(
        latitude = "50.94603935915518",
        longitude = "6.959302840118697",
        status = CustomerStatus.NEW_CUSTOMER
    ),
)