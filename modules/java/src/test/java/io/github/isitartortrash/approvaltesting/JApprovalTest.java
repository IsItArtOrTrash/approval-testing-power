package io.github.isitartortrash.approvaltesting;

import org.approvaltests.JsonApprovals;
import org.junit.jupiter.api.Test;

import static io.github.isitartortrash.TestOrderBuilderKt.aDefaultOrder;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.anOrderWasProcessed;
import static io.github.isitartortrash.approvaltesting.FakeFunctionalityKt.callRestEndpoint;

public class JApprovalTest {

  @Test
  void approvalTest() {
    String orderId = "someOrderId";
    ShopOrder order = aDefaultOrder(orderId);

    anOrderWasProcessed(order);

    JsonApprovals.verifyJson(callRestEndpoint(orderId));
  }
}
