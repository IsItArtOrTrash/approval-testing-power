package io.github.isitartortrash.approvaltesting.other;

import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;
import io.github.isitartortrash.approvaltesting.util.TestBase;
import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.isitartortrash.approvaltesting.util.DefaultTestOrderBuilder.aDefaultOrder;
import static org.assertj.core.api.Assertions.assertThat;

class FileComparisonTest extends TestBase {

  private final String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

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
