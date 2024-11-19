package io.github.isitartortrash.approvaltesting.other;

import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;

import static io.github.isitartortrash.approvaltesting.util.DefaultTestOrderBuilder.aDefaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class FileComparisonTest extends TestBase {

  @BeforeEach
  void setupClock() {
    Instant now = Instant.parse("2024-11-14T14:21:13.479337Z");
    given(clock.instant()).willReturn(now);
    given(clock.getZone()).willReturn(ZoneId.systemDefault());
    given(clock.millis()).willReturn(now.toEpochMilli());
  }

  @Test
  void assertionTest() throws IOException {
    String orderId = "someOrderId";
    IncomingOrder order = aDefaultOrder(orderId);

    orderService.sendOrPostIncomingData(order);

    String result = orderService.receiveOrGetOutgoingData(orderId);
    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedOrder.json")));
    assertThat(replaceBillingAddressId(result)).isEqualToIgnoringWhitespace(expected);
  }

  private String replaceBillingAddressId(String outgoingOrder) {
    return outgoingOrder.replaceAll("\"billingAddress\":\\{\"id\":".trim() + "\"[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}\"", "\"billingAddress\":\\{\"id\":".trim() + "\"\\[randomBillingAddressId\\]\"");
  }
}
