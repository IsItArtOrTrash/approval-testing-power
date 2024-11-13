package io.github.isitartortrash.approvaltesting.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.isitartortrash.approvaltesting.livecoding.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.github.isitartortrash.approvaltesting.DefaultTestOrderBuilder.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.TestUtils.jsonMapper;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.callRestEndpoint;
import static org.assertj.core.api.Assertions.assertThat;

class JManyObjectAssertionsTest {

  @Test
  void assertionTest() throws JsonProcessingException {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    OrderResult orderResult = jsonMapper.readValue(callRestEndpoint(orderId), OrderResult.class);

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
    assertThat(coupon.getId()).isEqualTo("someCouponId");
    assertThat(coupon.getDescription()).isEqualTo("Speaker Coupon");
    assertThat(coupon.getReducedRateInPercentage()).isEqualTo(100);

    assertThat(orderResult.getOrderTimeStamp()).isEqualTo(LocalDateTime.of(2024, 7, 19, 11, 45));
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
