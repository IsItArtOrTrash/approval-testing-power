package io.github.isitartortrash.approvaltesting.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.junit.jupiter.api.Test;

import static io.github.isitartortrash.approvaltesting.util.DefaultTestOrderBuilder.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.util.TestUtils.jsonMapper;
import static org.assertj.core.api.Assertions.assertThat;

class JManyJsonNodeAssertionsTest extends TestBase {

  @Test
  void assertionTest() throws JsonProcessingException {
    String orderId = "someOrderId";
    IncomingOrder order = aDefaultOrder(orderId);

    orderService.sendOrPostIncomingData(order);

    JsonNode result = jsonMapper.readTree(orderService.receiveOrGetOutgoingData(orderId));

    assertThat(result.get("id").asText()).isEqualTo("someOrderId");
    assertThat(result.get("version").asText()).isEqualTo("1");

    JsonNode item = result.get("items").get(0);
    assertThat(item.get("id").asText()).isEqualTo("someItemId");
    assertThat(item.get("name").asText()).isEqualTo("ATD 3 Conf. Days");
    assertThat(item.get("amount").asInt()).isEqualTo(2);

    JsonNode itemPrice = item.get("price");
    assertThat(itemPrice.get("value").asInt()).isEqualTo(225000);
    assertThat(itemPrice.get("monetaryUnit").asText()).isEqualTo("cent");
    assertThat(itemPrice.get("currency").asText()).isEqualTo("EUR");

    JsonNode coupon = result.get("coupons").get(0);
    assertThat(coupon.get("id").asText()).isEqualTo("someCouponId");
    assertThat(coupon.get("description").asText()).isEqualTo("Speaker Coupon");
    assertThat(coupon.get("reducedRateInPercentage").asInt()).isEqualTo(10);

    JsonNode orderTimeStamp = result.get("orderTimeStamp");
    assertThat(orderTimeStamp.asText()).isNotNull();

    JsonNode deliveryDate = result.get("deliveryDate");
    assertThat(deliveryDate.asText()).isEqualTo("2024-11-22");

    JsonNode shippingCost = result.get("shippingCost").get(0);
    assertThat(shippingCost.get("value").asInt()).isEqualTo(500);
    assertThat(shippingCost.get("monetaryUnit").asText()).isEqualTo("cent");
    assertThat(shippingCost.get("currency").asText()).isEqualTo("EUR");

    JsonNode customer = result.get("customer");
    assertThat(customer.get("id").asText()).isEqualTo(order.customerUuid().toString());
    assertThat(customer.get("firstName").asText()).isEqualTo("REWE");
    assertThat(customer.get("lastName").asText()).isEqualTo("Digital");

    JsonNode shippingAddress = result.get("shippingAddress");
    assertThat(shippingAddress.get("id").asText()).isEqualTo("1fbbb9b4-dd34-4930-b54e-d896a68ba343");
    assertThat(shippingAddress.get("firstName").asText()).isEqualTo("Janina");
    assertThat(shippingAddress.get("lastName").asText()).isEqualTo("Nemec");
    assertThat(shippingAddress.get("streetName").asText()).isEqualTo("Schanzenstr.");
    assertThat(shippingAddress.get("houseNumber").asText()).isEqualTo("6-20");
    assertThat(shippingAddress.get("postalCode").asText()).isEqualTo("51063");
    assertThat(shippingAddress.get("city").asText()).isEqualTo("Köln");
    assertThat(shippingAddress.get("country").asText()).isEqualTo("Deutschland");
    assertThat(shippingAddress.get("phone").asText()).isEqualTo("0221 9758420");
    assertThat(shippingAddress.get("latitude").asText()).isEqualTo("50.96490882194811");
    assertThat(shippingAddress.get("longitude").asText()).isEqualTo("7.014472855463499");
    assertThat(shippingAddress.get("email").asText()).isEqualTo("kontakt@rewe-digital.com");

    JsonNode billingAddress = result.get("billingAddress");
    assertThat(billingAddress.get("firstName").asText()).isEqualTo("Micha");
    assertThat(billingAddress.get("lastName").asText()).isEqualTo("Kutz");
    assertThat(billingAddress.get("streetName").asText()).isEqualTo("Domstr.");
    assertThat(billingAddress.get("houseNumber").asText()).isEqualTo("20");
    assertThat(billingAddress.get("postalCode").asText()).isEqualTo("50668");
    assertThat(billingAddress.get("city").asText()).isEqualTo("Köln");
    assertThat(billingAddress.get("country").asText()).isEqualTo("Deutschland");
    assertThat(billingAddress.get("phone").asText()).isEqualTo("+49 221 1490");
    assertThat(billingAddress.get("latitude").asText()).isEqualTo("50.94603935915518");
    assertThat(billingAddress.get("longitude").asText()).isEqualTo("6.959302840118697");
    assertThat(billingAddress.get("email").asText()).isEqualTo("info@rewe-group.com");
  }
}
