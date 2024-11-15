package io.github.isitartortrash.approvaltesting.other;

import io.github.isitartortrash.approvaltesting.FakeOrderService;
import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;
import io.github.isitartortrash.approvaltesting.OrderService;
import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;

import static io.github.isitartortrash.approvaltesting.DefaultTestOrderBuilder.aDefaultOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class FileComparisonTest {

  private final String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

  private OrderService orderService = new FakeOrderService(mock(Clock.class));

  @Test
  void assertionTest() throws IOException {
    String orderId = "someOrderId";
    IncomingOrder order = aDefaultOrder(orderId);

    orderService.sendIncomingData(order);

    String result = orderService.getOutgoingData(orderId);

    JsonApprovals.verifyJson(result);

    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedOrder.json")));
    assertThat(result).isEqualToIgnoringWhitespace(expected);
  }
}
