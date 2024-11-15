package io.github.isitartortrash.approvaltesting;

import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;

public interface OrderService {
  public void sendIncomingData(IncomingOrder incomingOrder);
  public String getOutgoingData(String orderId);
}
