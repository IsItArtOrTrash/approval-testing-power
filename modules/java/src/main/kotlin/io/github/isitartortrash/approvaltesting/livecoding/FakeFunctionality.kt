package io.github.isitartortrash.approvaltesting.livecoding

import com.fasterxml.jackson.databind.DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES
import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE
import com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS
import com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule

val jsonMapper: JsonMapper = JsonMapper
    .builder()
    .disable(WRITE_DATES_AS_TIMESTAMPS)
    .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
    .disable(FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
    .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
    .enable(FAIL_ON_MISSING_CREATOR_PROPERTIES, FAIL_ON_NULL_FOR_PRIMITIVES)
    .addModule(JavaTimeModule())
    .addModule(Jdk8Module())
    .addModule(kotlinModule())
    .build()

val savedOrders = mutableMapOf<String, OrderResult>()

fun sendIngoingData(shopOrder: ShopOrder) {
    savedOrders[shopOrder.id] = shopOrder.enrich()
}

fun getOutgoingData(orderId: String): String? {
    return jsonMapper.writeValueAsString(savedOrders.get(orderId)!!)
}

