package io.github.isitartortrash.approvaltesting.livecoding;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.github.isitartortrash.approvaltesting.livecoding.AddressBuilder.anAddress;
import static io.github.isitartortrash.approvaltesting.livecoding.CouponBuilder.aCoupon;
import static io.github.isitartortrash.approvaltesting.livecoding.CustomerBuilder.aCustomer;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.getOutgoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.sendIngoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.ItemBuilder.anItem;
import static io.github.isitartortrash.approvaltesting.livecoding.OrderBuilder.anOrder;
import static io.github.isitartortrash.approvaltesting.livecoding.PriceBuilder.aPrice;
import static org.assertj.core.api.Assertions.assertThat;

public class NewStringAssertionTest {

  @Test
  void assertionTest() {
    String orderId = "someOrderId";
    ShopPrice givenItemPrice = aPrice()
        .value(225000)
        .monetaryUnit("cent")
        .currency("EUR")
        .build();
    ShopItem givenItem = anItem()
        .id("someItemId")
        .name("ATD 3 Conf. Days")
        .amount(2)
        .price(givenItemPrice)
        .build();
    ShopCoupon givenCoupon = aCoupon()
        .id("speakerCouponId")
        .description("Speaker Coupon")
        .build();
    ShopCustomer givenCustomer = aCustomer()
        .id("someCustomerId")
        .firstName("REWE")
        .lastName("Digital")
        .build();
    ShopAddress givenShippingAddress = anAddress()
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
        .build();
    ShopAddress givenBillingAddress = anAddress()
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
        .build();

    ShopOrder order = anOrder()
        .id(orderId)
        .version(1)
        .items(List.of(givenItem))
        .coupons(List.of(givenCoupon))
        .deliveryDate(LocalDate.of(2024, 11, 22))
        .customer(givenCustomer)
        .shippingAddress(givenShippingAddress)
        .billingAddress(givenBillingAddress)
        .build();

    sendIngoingData(order);

    String result = getOutgoingData(orderId);

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
                    "houseNumber": "6-20",
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
