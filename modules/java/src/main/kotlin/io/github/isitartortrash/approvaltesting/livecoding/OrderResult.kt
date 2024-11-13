package io.github.isitartortrash.approvaltesting.livecoding

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import java.time.LocalDate
import java.time.LocalDateTime

data class OrderResult @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("version") val version: Int,
    @JsonProperty("items") val items: List<ItemResult>,
    @JsonProperty("coupons") val coupons: List<CouponResult> = emptyList(),
    @JsonProperty("orderTimeStamp") val orderTimeStamp: LocalDateTime,
    @JsonProperty("deliveryDate") val deliveryDate: LocalDate,
    @JsonProperty("shippingCost") val shippingCost: List<PriceResult>,
    @JsonProperty("customer") val customer: CustomerResult,
    @JsonProperty("shippingAddress") val shippingAddress: AddressResult,
    @JsonProperty("billingAddress") val billingAddress: AddressResult
)

data class ItemResult @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("amount") val amount: Int,
    @JsonProperty("price") val price: PriceResult
)

data class CouponResult @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("reducedRateInPercentage") val reducedRateInPercentage: Int
)

data class PriceResult @JsonCreator constructor(
    @JsonProperty("value") val value: Int,
    @JsonProperty("monetaryUnit") val monetaryUnit: String,
    @JsonProperty("currency") val currency: String
)

data class CustomerResult @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String
)

data class AddressResult @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("firstName") val firstName: String,
    @JsonProperty("lastName") val lastName: String,
    @JsonProperty("streetName") val streetName: String,
    @JsonProperty("houseNumber") val houseNumber: String,
    @JsonProperty("city") val city: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("phone") val phone: String,
    @JsonProperty("latitude") val latitude: String,
    @JsonProperty("longitude") val longitude: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("postalCode") val postalCode: String,
    @JsonProperty("status") val status: CustomerStatus,
)

enum class CustomerStatus {
    NEW_CUSTOMER,
    KNOWN_CUSTOMER;

    @JsonValue
    fun value() = name.lowercase()
}
