package io.github.isitartortrash.approvaltesting.livecoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static io.github.isitartortrash.approvaltesting.TestUtils.jsonMapper;
import static io.github.isitartortrash.approvaltesting.livecoding.AddressBuilder.anAddress;
import static io.github.isitartortrash.approvaltesting.livecoding.CouponBuilder.aCoupon;
import static io.github.isitartortrash.approvaltesting.livecoding.CustomerBuilder.aCustomer;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.getOutgoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.sendIngoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.ItemBuilder.anItem;
import static io.github.isitartortrash.approvaltesting.livecoding.OrderBuilder.anOrder;
import static io.github.isitartortrash.approvaltesting.livecoding.PriceBuilder.aPrice;
import static org.assertj.core.api.Assertions.assertThat;

class OldObjectAssertionsTest {

  @Test
  void assertionTest() throws JsonProcessingException {
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

    OrderResult orderResult = jsonMapper.readValue(getOutgoingData(orderId), OrderResult.class);

    assertThat(orderResult.getId()).isEqualTo("someOrderId");
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

    assertThat(orderResult.getDeliveryDate()).isEqualTo(LocalDate.of(2024, 11, 22));

    PriceResult shippingCost = orderResult.getShippingCost().getFirst();
    assertThat(shippingCost.getValue()).isEqualTo(500);
    assertThat(shippingCost.getMonetaryUnit()).isEqualTo("cent");
    assertThat(shippingCost.getCurrency()).isEqualTo("EUR");

    CustomerResult customer = orderResult.getCustomer();
    assertThat(customer.getId()).isEqualTo("someCustomerId");
    assertThat(customer.getFirstName()).isEqualTo("REWE");
    assertThat(customer.getLastName()).isEqualTo("Digital");

    AddressResult shippingAddress = orderResult.getShippingAddress();
    assertThat(shippingAddress.getId()).isEqualTo("someShippingAddressId");
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
    assertThat(billingAddress.getId()).isEqualTo("someBillingAddressId");
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
