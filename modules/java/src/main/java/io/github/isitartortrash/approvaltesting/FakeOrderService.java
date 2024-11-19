package io.github.isitartortrash.approvaltesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.isitartortrash.approvaltesting.incoming.*;
import io.github.isitartortrash.approvaltesting.outgoing.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.github.isitartortrash.approvaltesting.incoming.Currency.EUR;

public class FakeOrderService implements OrderService {

  private final Clock clock;
  private final JsonMapper jsonMapper = JsonMapper
      .builder()
      .disable(WRITE_DATES_AS_TIMESTAMPS)
      .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
      .disable(FAIL_ON_UNKNOWN_PROPERTIES)
      .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
      .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
      .enable(FAIL_ON_MISSING_CREATOR_PROPERTIES, FAIL_ON_NULL_FOR_PRIMITIVES)
      .addModule(new JavaTimeModule())
      .addModule(new Jdk8Module())
      .build();

  public FakeOrderService(Clock clock) {
    this.clock = clock;
  }

  private final Map<String, String> savedOrders = new HashMap<>();

  @Override
  public void sendOrPostIncomingData(IncomingOrder incomingOrder) {
    try {
      savedOrders.put(incomingOrder.id(), jsonMapper.writeValueAsString(incomingOrder));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String receiveOrGetOutgoingData(String orderId) {
    try {
      final IncomingOrder incomingOrder = jsonMapper.readValue(savedOrders.get(orderId), IncomingOrder.class);
      return jsonMapper.writeValueAsString(enrichOrder(incomingOrder, LocalDateTime.now(clock)));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private OutgoingOrder enrichOrder(IncomingOrder incomingOrder, LocalDateTime orderTimeStamp) {
    return io.github.isitartortrash.approvaltesting.outgoing.OutgoingOrder.builder()
        .id(incomingOrder.id())
        .version(1L)
        .items(incomingOrder.items().stream().map(this::enrichItem).toList())
        .coupons(incomingOrder.coupons().stream().map(this::enrichCoupon).toList())
        .orderTimeStamp(orderTimeStamp)
        .deliveryDate(incomingOrder.deliveryDate())
        .shippingCost(List.of(
            OutgoingPrice.builder()
                .value(500)
                .monetaryUnit(EUR.getMonetaryUnit())
                .currency(EUR.name())
                .build()
        ))
        .customer(enrichCustomer(incomingOrder.customerUuid()))
        .shippingAddress(
            enrichAddress(
                incomingOrder.shippingAddress(),
                "50.96490882194811",
                "7.014472855463499",
                CustomerStatus.KNOWN_CUSTOMER,
                UUID.fromString("1fbbb9b4-dd34-4930-b54e-d896a68ba343")
            )
        )
        .billingAddress(
            enrichAddress(
                incomingOrder.billingAddress(),
                "50.94603935915518",
                "6.959302840118697",
                CustomerStatus.NEW_CUSTOMER,
                UUID.randomUUID()
            )
        )
        .build();
  }

  private OutgoingItem enrichItem(IncomingItem incomingItem) {
    return OutgoingItem.builder()
        .id(incomingItem.id())
        .name(incomingItem.name())
        .amount(incomingItem.amount())
        .price(enrichPrice(incomingItem.price()))
        .build();
  }

  private OutgoingPrice enrichPrice(IncomingPrice incomingPrice) {
    return OutgoingPrice.builder()
        .value(incomingPrice.value())
        .monetaryUnit(incomingPrice.currency().getMonetaryUnit())
        .currency(incomingPrice.currency().name())
        .build();
  }

  private OutgoingCoupon enrichCoupon(IncomingCoupon incomingCoupon) {
    return OutgoingCoupon.builder()
        .id(incomingCoupon.id())
        .description(incomingCoupon.description())
        .reducedRateInPercentage("speakerCouponId".equals(incomingCoupon.id()) ? 100 : 10)
        .build();
  }

  private OutgoingAddress enrichAddress(
      IncomingAddress incomingAddress,
      String latitude,
      String longitude,
      CustomerStatus status,
      UUID id) {
    return OutgoingAddress.builder()
        .id(id.toString())
        .firstName(incomingAddress.firstName())
        .lastName(incomingAddress.lastName())
        .streetName(incomingAddress.streetName())
        .houseNumber(incomingAddress.houseNumber())
        .postalCode(incomingAddress.postalCode())
        .city(incomingAddress.city())
        .country(incomingAddress.country())
        .phone(incomingAddress.phone())
        .email(incomingAddress.email())
        .latitude(latitude)
        .longitude(longitude)
        .status(status)
        .build();
  }

  private OutgoingCustomer enrichCustomer(UUID id) {
    if (UUID.fromString("9e71d9c1-a066-41e0-a79e-061089110d85").equals(id)) {
      return OutgoingCustomer.builder()
          .id(id.toString())
          .firstName("REWE")
          .lastName("Digital")
          .build();
    }
    return OutgoingCustomer.builder()
        .id(id.toString())
        .firstName("UNKNOWN")
        .lastName("UNKNOWN")
        .build();
  }

}

