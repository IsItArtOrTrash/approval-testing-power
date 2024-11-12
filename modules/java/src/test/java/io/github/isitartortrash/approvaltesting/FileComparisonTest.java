package io.github.isitartortrash.approvaltesting;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.isitartortrash.approvaltesting.DefaultTestOrderBuilder.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.callRestEndpoint;
import static org.assertj.core.api.Assertions.assertThat;

class FileComparisonTest {

  private final String TEST_DIR = "src/test/resources/json/FileComparisonTest/";

  @Test
  void assertionTest() throws IOException {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    String result = callRestEndpoint(orderId);

    String expected = new String(Files.readAllBytes(Paths.get(TEST_DIR + "expectedOrder.json")));
    assertThat(result).isEqualToIgnoringWhitespace(expected);
  }
}
