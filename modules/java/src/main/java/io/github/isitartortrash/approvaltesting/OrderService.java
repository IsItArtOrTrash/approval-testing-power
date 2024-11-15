package io.github.isitartortrash.approvaltesting;

import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;

public interface OrderService {
  void sendIncomingData(IncomingOrder incomingOrder);
  String getOutgoingData(String orderId);
}
