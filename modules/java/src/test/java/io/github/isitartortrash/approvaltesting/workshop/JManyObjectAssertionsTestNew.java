package io.github.isitartortrash.approvaltesting.workshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.isitartortrash.approvaltesting.*;
import io.github.isitartortrash.approvaltesting.livecoding.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static io.github.isitartortrash.approvaltesting.Currency.EUR;
import static io.github.isitartortrash.approvaltesting.TestUtils.jsonMapper;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.getOutgoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.sendIngoingData;
import static org.assertj.core.api.Assertions.assertThat;

class JManyObjectAssertionsTestNew {

  @Test
  void assertionTest() throws JsonProcessingException {
    // given
    String orderId = "someOrderId";
    LocalDate deliveryDate = LocalDate.of(2024, 11, 22);
    UUID customerUuid = UUID.fromString("9e71d9c1-a066-41e0-a79e-061089110d85");

    IncomingOrder inOrder = IncomingOrder.builder()
        .id(orderId)
        .items(List.of(
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
        ))
        .coupons(List.of(
            IncomingCoupon.builder()
                .id("speakerCouponId")
                .description("Speaker Coupon")
                .build()))
        .deliveryDate(deliveryDate)
        .customerUuid(customerUuid)
        .shippingAddress(IncomingAddress.builder()
            .firstName("Janina")
            .lastName("Nemec")
            .streetName("Schanzenstr.")
            .houseNumber("6-20")
            .postalCode("51063")
            .city("Köln")
            .country("Deutschland")
            .phone("0221 9758420")
            .email("kontakt@rewe-digital.com")
            .build())
        .billingAddress(IncomingAddress.builder()
            .firstName("Micha")
            .lastName("Kutz")
            .streetName("Domstr.")
            .houseNumber("20")
            .postalCode("50668")
            .city("Köln")
            .country("Deutschland")
            .phone("+49 221 1490")
            .email("info@rewe-group.com")
            .build())
        .build();

    sendIngoingData(inOrder);

    OrderResult orderResult = jsonMapper.readValue(getOutgoingData(orderId), OrderResult.class);

    assertThat(orderResult.getId()).isEqualTo(orderId);
    assertThat(orderResult.getVersion()).isEqualTo(1);

    ItemResult item = orderResult.getItems().getFirst();
    assertThat(item.getId()).isEqualTo("someItemId");
    assertThat(item.getName()).isEqualTo("ATD 3 Conf. Days");
    assertThat(item.getAmount()).isEqualTo(2);

    PriceResult itemPrice = item.getPrice();
    assertThat(itemPrice.getValue()).isEqualTo(225000);
    assertThat(itemPrice.getMonetaryUnit()).isEqualTo("cent");
    assertThat(itemPrice.getCurrency()).isEqualTo("EUR");

    CouponResult coupon = orderResult.getCoupons().getFirst();
    assertThat(coupon.getId()).isEqualTo("speakerCouponId");
    assertThat(coupon.getDescription()).isEqualTo("Speaker Coupon");

    assertThat(orderResult.getOrderTimeStamp()).isEqualToIgnoringHours(LocalDateTime.now());
    assertThat(orderResult.getDeliveryDate()).isEqualTo(deliveryDate);

    CustomerResult customer = orderResult.getCustomer();
    assertThat(customer.getId()).isEqualTo(inOrder.customerUuid().toString());
    assertThat(customer.getFirstName()).isEqualTo("REWE");
    assertThat(customer.getLastName()).isEqualTo("Digital");

    AddressResult shippingAddress = orderResult.getShippingAddress();
    assertThat(shippingAddress.getId()).isEqualTo("1fbbb9b4-dd34-4930-b54e-d896a68ba343");
    assertThat(shippingAddress.getFirstName()).isEqualTo("Janina");
    assertThat(shippingAddress.getLastName()).isEqualTo("Nemec");
    assertThat(shippingAddress.getStreetName()).isEqualTo("Schanzenstr.");
    assertThat(shippingAddress.getHouseNumber()).isEqualTo("6-20");
    assertThat(shippingAddress.getPostalCode()).isEqualTo("51063");
    assertThat(shippingAddress.getCity()).isEqualTo("Köln");
    assertThat(shippingAddress.getCountry()).isEqualTo("Deutschland");
    assertThat(shippingAddress.getPhone()).isEqualTo("0221 9758420");
    assertThat(shippingAddress.getLatitude()).isEqualTo("50.96490882194811");
    assertThat(shippingAddress.getLongitude()).isEqualTo("7.014472855463499");
    assertThat(shippingAddress.getEmail()).isEqualTo("kontakt@rewe-digital.com");

    AddressResult billingAddress = orderResult.getBillingAddress();
    assertThat(billingAddress.getId()).isNotNull();
    assertThat(billingAddress.getFirstName()).isEqualTo("Micha");
    assertThat(billingAddress.getLastName()).isEqualTo("Kutz");
    assertThat(billingAddress.getStreetName()).isEqualTo("Domstr.");
    assertThat(billingAddress.getHouseNumber()).isEqualTo("20");
    assertThat(billingAddress.getPostalCode()).isEqualTo("50668");
    assertThat(billingAddress.getCity()).isEqualTo("Köln");
    assertThat(billingAddress.getCountry()).isEqualTo("Deutschland");
    assertThat(billingAddress.getPhone()).isEqualTo("+49 221 1490");
    assertThat(billingAddress.getLatitude()).isEqualTo("50.94603935915518");
    assertThat(billingAddress.getLongitude()).isEqualTo("6.959302840118697");
    assertThat(billingAddress.getEmail()).isEqualTo("info@rewe-group.com");
  }
}
