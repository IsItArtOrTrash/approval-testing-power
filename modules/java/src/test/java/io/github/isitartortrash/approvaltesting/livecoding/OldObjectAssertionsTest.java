package io.github.isitartortrash.approvaltesting.livecoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.isitartortrash.approvaltesting.incoming.*;
import io.github.isitartortrash.approvaltesting.outgoing.*;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.isitartortrash.approvaltesting.incoming.Currency.EUR;
import static io.github.isitartortrash.approvaltesting.util.TestUtils.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

class OldObjectAssertionsTest extends TestBase {

  @Test
  @Disabled
  void assertionTest() throws JsonProcessingException {
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

    orderService.sendOrPostIncomingData(incomingOrder);

    OutgoingOrder orderResult = jsonMapper.readValue(orderService.receiveOrGetOutgoingData(orderId), OutgoingOrder.class);

    assertThat(orderResult.id()).isEqualTo("someOrderId");
    assertThat(orderResult.version()).isEqualTo(1);

    OutgoingItem item = orderResult.items().getFirst();
    assertThat(item.id()).isEqualTo("someItemId");
    assertThat(item.name()).isEqualTo("ATD 3 Conf. Days");
    assertThat(item.amount()).isEqualTo(2);

    OutgoingPrice itemPrice = item.price();
    assertThat(itemPrice.value()).isEqualTo(225000);
    assertThat(itemPrice.monetaryUnit()).isEqualTo("cent");
    assertThat(itemPrice.currency()).isEqualTo("EUR");

    OutgoingCoupon coupon = orderResult.coupons().getFirst();
    assertThat(coupon.id()).isEqualTo("speakerCouponId");
    assertThat(coupon.description()).isEqualTo("Speaker Coupon");

    assertThat(orderResult.deliveryDate()).isEqualTo(LocalDate.of(2024, 11, 22));

    OutgoingPrice shippingCost = orderResult.shippingCost().getFirst();
    assertThat(shippingCost.value()).isEqualTo(500);
    assertThat(shippingCost.monetaryUnit()).isEqualTo("cent");
    assertThat(shippingCost.currency()).isEqualTo("EUR");

    OutgoingCustomer customer = orderResult.customer();
    assertThat(customer.id()).isEqualTo("someCustomerId");
    assertThat(customer.firstName()).isEqualTo("REWE");
    assertThat(customer.lastName()).isEqualTo("Digital");

    OutgoingAddress shippingAddress = orderResult.shippingAddress();
    assertThat(shippingAddress.id()).isEqualTo("someShippingAddressId");
    assertThat(shippingAddress.firstName()).isEqualTo("Janina");
    assertThat(shippingAddress.lastName()).isEqualTo("Nemec");
    assertThat(shippingAddress.streetName()).isEqualTo("Schanzenstr.");
    assertThat(shippingAddress.houseNumber()).isEqualTo("6-20");
    assertThat(shippingAddress.postalCode()).isEqualTo("51063");
    assertThat(shippingAddress.city()).isEqualTo("Köln");
    assertThat(shippingAddress.country()).isEqualTo("Deutschland");
    assertThat(shippingAddress.phone()).isEqualTo("0221 9758420");
    assertThat(shippingAddress.latitude()).isEqualTo("50.96490882194811");
    assertThat(shippingAddress.longitude()).isEqualTo("7.014472855463499");
    assertThat(shippingAddress.email()).isEqualTo("kontakt@rewe-digital.com");

    OutgoingAddress billingAddress = orderResult.billingAddress();
    assertThat(billingAddress.id()).isEqualTo("someBillingAddressId");
    assertThat(billingAddress.firstName()).isEqualTo("Micha");
    assertThat(billingAddress.lastName()).isEqualTo("Kutz");
    assertThat(billingAddress.streetName()).isEqualTo("Domstr.");
    assertThat(billingAddress.houseNumber()).isEqualTo("20");
    assertThat(billingAddress.postalCode()).isEqualTo("50668");
    assertThat(billingAddress.city()).isEqualTo("Köln");
    assertThat(billingAddress.country()).isEqualTo("Deutschland");
    assertThat(billingAddress.phone()).isEqualTo("+49 221 1490");
    assertThat(billingAddress.latitude()).isEqualTo("50.94603935915518");
    assertThat(billingAddress.longitude()).isEqualTo("6.959302840118697");
    assertThat(billingAddress.email()).isEqualTo("info@rewe-group.com");
  }
}
