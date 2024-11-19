package io.github.isitartortrash.approvaltesting.workshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.isitartortrash.approvaltesting.incoming.*;
import io.github.isitartortrash.approvaltesting.outgoing.*;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.github.isitartortrash.approvaltesting.incoming.Currency.EUR;
import static io.github.isitartortrash.approvaltesting.outgoing.CustomerStatus.KNOWN_CUSTOMER;
import static io.github.isitartortrash.approvaltesting.outgoing.CustomerStatus.NEW_CUSTOMER;
import static io.github.isitartortrash.approvaltesting.util.TestUtils.jsonMapper;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest extends TestBase {

  @Test
  void assertionTest() throws JsonProcessingException {
    // given
    String orderId = "someOrderId";
    LocalDate deliveryDate = LocalDate.of(2024, 11, 22);
    UUID customerUuid = UUID.fromString("9e71d9c1-a066-41e0-a79e-061089110d85");

    IncomingAddress givenShippingAddress = IncomingAddress.builder()
        .firstName("Janina")
        .lastName("Nemec")
        .streetName("Schanzenstr.")
        .houseNumber("6-20")
        .postalCode("51063")
        .city("Köln")
        .country("Deutschland")
        .phone("0221 9758420")
        .email("kontakt@rewe-digital.com")
        .build();

    IncomingAddress givenBillingAddress = IncomingAddress.builder()
        .firstName("Micha")
        .lastName("Kutz")
        .streetName("Domstr.")
        .houseNumber("20")
        .postalCode("50668")
        .city("Köln")
        .country("Deutschland")
        .phone("+49 221 1490")
        .email("info@rewe-group.com")
        .build();

    IncomingCoupon givenCoupon = IncomingCoupon.builder()
        .id("speakerCouponId")
        .description("Speaker Coupon")
        .build();

    IncomingItem givenItem = IncomingItem.builder()
        .id("someItemId")
        .name("ATD 3 Conf. Days")
        .amount(2)
        .price(IncomingPrice.builder().value(225000).currency(EUR).build())
        .build();

    IncomingOrder incomingOrder = IncomingOrder.builder()
        .id(orderId)
        .items(List.of(givenItem))
        .coupons(List.of(givenCoupon))
        .deliveryDate(deliveryDate)
        .customerUuid(customerUuid)
        .shippingAddress(givenShippingAddress)
        .billingAddress(givenBillingAddress)
        .build();

    // when
    orderService.sendOrPostIncomingData(incomingOrder);
    String outgoingOrderAsString = orderService.receiveOrGetOutgoingData(orderId);

    // then
    OutgoingOrder outgoingOrder = jsonMapper.readValue(outgoingOrderAsString, OutgoingOrder.class);

    assertThat(outgoingOrder.id()).isEqualTo(orderId);
    assertThat(outgoingOrder.version()).isEqualTo(1);

    OutgoingItem item = outgoingOrder.items().getFirst();
    assertThat(item.id()).isEqualTo("someItemId");
    assertThat(item.name()).isEqualTo("ATD 3 Conf. Days");
    assertThat(item.amount()).isEqualTo(2);

    OutgoingPrice itemPrice = item.price();
    assertThat(itemPrice.value()).isEqualTo(225000);
    assertThat(itemPrice.monetaryUnit()).isEqualTo("cent");
    assertThat(itemPrice.currency()).isEqualTo("EUR");

    OutgoingCoupon coupon = outgoingOrder.coupons().getFirst();
    assertThat(coupon.id()).isEqualTo("speakerCouponId");
    assertThat(coupon.description()).isEqualTo("Speaker Coupon");

    assertThat(LocalDateTime.ofInstant(outgoingOrder.orderTimeStamp(), UTC)).isEqualToIgnoringHours(LocalDateTime.now());
    assertThat(outgoingOrder.deliveryDate()).isEqualTo(deliveryDate);

    OutgoingCustomer customer = outgoingOrder.customer();
    assertThat(customer.id()).isEqualTo(incomingOrder.customerUuid().toString());
    assertThat(customer.firstName()).isEqualTo("REWE");
    assertThat(customer.lastName()).isEqualTo("Digital");

    OutgoingAddress shippingAddress = outgoingOrder.shippingAddress();
    assertThat(shippingAddress.id()).isEqualTo("1fbbb9b4-dd34-4930-b54e-d896a68ba343");
    assertThat(shippingAddress.firstName()).isEqualTo(givenShippingAddress.firstName());
    assertThat(shippingAddress.lastName()).isEqualTo(givenShippingAddress.lastName());
    assertThat(shippingAddress.streetName()).isEqualTo(givenShippingAddress.streetName());
    assertThat(shippingAddress.houseNumber()).isEqualTo(givenShippingAddress.houseNumber());
    assertThat(shippingAddress.postalCode()).isEqualTo(givenShippingAddress.postalCode());
    assertThat(shippingAddress.city()).isEqualTo(givenShippingAddress.city());
    assertThat(shippingAddress.country()).isEqualTo(givenShippingAddress.country());
    assertThat(shippingAddress.phone()).isEqualTo(givenShippingAddress.phone());
    assertThat(shippingAddress.email()).isEqualTo(givenShippingAddress.email());
    assertThat(shippingAddress.status()).isEqualTo(KNOWN_CUSTOMER);

    OutgoingAddress billingAddress = outgoingOrder.billingAddress();
    assertThat(billingAddress.id()).isNotNull();
    assertThat(billingAddress.firstName()).isEqualTo(givenBillingAddress.firstName());
    assertThat(billingAddress.lastName()).isEqualTo(givenBillingAddress.lastName());
    assertThat(billingAddress.streetName()).isEqualTo(givenBillingAddress.streetName());
    assertThat(billingAddress.houseNumber()).isEqualTo(givenBillingAddress.houseNumber());
    assertThat(billingAddress.postalCode()).isEqualTo(givenBillingAddress.postalCode());
    assertThat(billingAddress.city()).isEqualTo(givenBillingAddress.city());
    assertThat(billingAddress.country()).isEqualTo(givenBillingAddress.country());
    assertThat(billingAddress.phone()).isEqualTo(givenBillingAddress.phone());
    assertThat(billingAddress.email()).isEqualTo(givenBillingAddress.email());
    assertThat(billingAddress.status()).isEqualTo(NEW_CUSTOMER);
  }

}
