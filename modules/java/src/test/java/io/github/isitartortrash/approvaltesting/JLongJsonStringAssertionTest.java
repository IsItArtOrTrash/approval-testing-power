package io.github.isitartortrash.approvaltesting;

import org.junit.jupiter.api.Test;

import static io.github.isitartortrash.approvaltesting.DefaultTestOrderBuilder.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.callRestEndpoint;
import static org.assertj.core.api.Assertions.assertThat;

public class JLongJsonStringAssertionTest {

  @Test
  void assertionTest() {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    String result = callRestEndpoint(orderId);

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
                      "id": "someCouponId",
                      "description": "Speaker Coupon",
                      "reducedRateInPercentage": 100
                    }
                  ],
                  "orderTimeStamp":"2024-07-19T11:45:00",
                  "deliveryDate":"2024-11-22",
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
                    "streetName": "Schanzenstr.",
                    "houseNumber": "6-20",
                    "city": "Köln",
                    "country": "Deutschland",
                    "phone": "0221 9758420",
                    "latitude": "50.96490882194811",
                    "longitude": "7.014472855463499",
                    "email": "kontakt@rewe-digital.com",
                    "postalCode": "51063",
                    "status":"known_customer"
                  },
                  "billingAddress": {
                    "id": "someBillingAddressId",
                    "firstName": "Micha",
                    "lastName": "Kutz",
                    "streetName": "Domstr.",
                    "houseNumber": "20",
                    "city": "Köln",
                    "country": "Deutschland",
                    "phone": "+49 221 1490",
                    "latitude": "50.94603935915518",
                    "longitude": "6.959302840118697",
                    "email": "info@rewe-group.com",
                    "postalCode": "50668",
                    "status":"new_customer"
                  }
                                }""");
  }
}
