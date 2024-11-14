package io.github.isitartortrash.approvaltesting.other;

import io.github.isitartortrash.approvaltesting.livecoding.ShopOrder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.isitartortrash.approvaltesting.DefaultTestOrderBuilder.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.getOutgoingData;
import static io.github.isitartortrash.approvaltesting.livecoding.FakeFunctionalityKt.sendIngoingData;
import static org.assertj.core.api.Assertions.assertThat;

class FileComparisonTest {

  private final String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

  @Test
  void assertionTest() throws IOException {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    sendIngoingData(order);

    String result = getOutgoingData(orderId);

    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedOrder.json")));
    assertThat(result).isEqualToIgnoringWhitespace(expected);
  }
}
