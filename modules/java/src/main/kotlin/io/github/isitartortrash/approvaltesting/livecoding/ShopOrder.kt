package io.github.isitartortrash.approvaltesting.livecoding

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.UUID

data class ShopOrder @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("items") val items: List<ShopItem>,
    @JsonProperty("coupons") val coupons: List<ShopCoupon> = emptyList(),
    @JsonProperty("deliveryDate") val deliveryDate: LocalDate,
    @JsonProperty("customerUuid") val customerUuid: UUID,
    @JsonProperty("shippingAddress") val shippingAddress: ShopAddress,
    @JsonProperty("billingAddress") val billingAddress: ShopAddress
)

data class ShopItem @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("amount") val amount: Int,
    @JsonProperty("price") val price: ShopPrice
)

data class ShopCoupon @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("description") val description: String
)

data class ShopPrice @JsonCreator constructor(
    @JsonProperty("value") val value: Int,
    @JsonProperty("currency") val currency: Currency
)

data class ShopAddress @JsonCreator constructor(
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("streetName") val streetName: String,
    @JsonProperty("houseNumber") val houseNumber: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("phone") val phone: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("postalCode") val postalCode: String
)

enum class Currency(val monetaryUnit: String) {
    EUR("cent"),
    USD("cent");
}
