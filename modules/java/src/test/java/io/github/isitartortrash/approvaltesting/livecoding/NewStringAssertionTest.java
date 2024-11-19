package io.github.isitartortrash.approvaltesting.livecoding;

import io.github.isitartortrash.approvaltesting.incoming.*;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static io.github.isitartortrash.approvaltesting.incoming.Currency.EUR;
import static org.assertj.core.api.Assertions.assertThat;

public class NewStringAssertionTest extends TestBase {

  @Test
  @Disabled
  void assertionTest() {
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

    String result = orderService.receiveOrGetOutgoingData(orderId);

    assertThat(result)
        .isEqualToIgnoringWhitespace(
            """
                {
                  "id": "someOrderId",
                  "version": 1,
                  "items": [
                    {
                      "id": "someItemId",
                      "name": "ATD 3 Conf. Days",
                      "amount": 2,
                      "price": {
                        "value": 225000,
                        "monetaryUnit": "cent",
                        "currency": "EUR"
                      }
                    }
                  ],
                  "coupons": [
                    {
                      "id": "speakerCouponId",
                      "description": "Speaker Coupon"
                    }
                  ],
                  "orderTimeStamp":"2024-07-19T11:45:00",
                  "shippingCost": [
                    {
                      "value": 500,
                      "monetaryUnit": "cent",
                      "currency": "EUR"
                    }
                  ],
                  "customer": {
                    "id": "someCustomerId",
                    "firstName": "REWE",
                    "lastName": "Digital"
                  },
                  "shippingAddress": {
                    "id": "someShippingAddressId",
                    "firstName": "Janina",
                    "lastName": "Nemec",
                    "streetName": "Schanzenstr."
                    "houseNumber":  "6-20",
                    "city": "Köln",
                    "country": "Deutschland",
                    "phone": "0221 9758420",
                    "latitude": "50.96490882194811",
                    "longitude": "7.014472855463499",
                    "email": "kontakt@rewe-digital.com",
                    "postalCode": "51063",
                    "status":"KNOWN_CUSTOMER"
                  },
                  "billingAddress": {
                    "id": "someBillingAddressId",
                    "firstName": "Micha",
                    "lastName": "Kutz",
                    "streetName": "Domstr. ",
                    "houseNumber": "20",
                    "city": "Köln",
                    "country": "Deutschland",
                    "phone": "+49 221 1490",
                    "latitude": "50.94603935915518",
                    "longitude": "6.959302840118697",
                    "email": "info@rewe-group.com",
                    "postalCode": "50668",
                    "status":"NEW_CUSTOMER"
                  }
                                }""");
  }
}
