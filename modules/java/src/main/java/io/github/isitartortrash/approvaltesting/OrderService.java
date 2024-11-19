package io.github.isitartortrash.approvaltesting;

import io.github.isitartortrash.approvaltesting.incoming.IncomingOrder;

public interface OrderService {
  void sendOrPostIncomingData(IncomingOrder incomingOrder);

  String receiveOrGetOutgoingData(String orderId);
}
