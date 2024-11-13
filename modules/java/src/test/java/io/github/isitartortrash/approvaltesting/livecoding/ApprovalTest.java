package io.github.isitartortrash.approvaltesting.livecoding;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.github.isitartortrash.approvaltesting.livecoding.AddressBuilder.anAddress;
import static io.github.isitartortrash.approvaltesting.livecoding.CouponBuilder.aCoupon;
import static io.github.isitartortrash.approvaltesting.livecoding.CustomerBuilder.aCustomer;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.callRestEndpoint;
import static io.github.isitartortrash.approvaltesting.livecoding.ItemBuilder.anItem;
import static io.github.isitartortrash.approvaltesting.livecoding.OrderBuilder.anOrder;
import static io.github.isitartortrash.approvaltesting.livecoding.PriceBuilder.aPrice;

public class ApprovalTest {

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

    anOrderWasProcessed(order);

    String result = callRestEndpoint(orderId);

    JsonApprovals.verifyJson(result);
  }

}
