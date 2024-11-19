package io.github.isitartortrash.approvaltesting.outgoing;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerStatus {
  NEW_CUSTOMER,
  KNOWN_CUSTOMER;

  @JsonValue
  String value() {
    return this.name().toLowerCase();
  }
}
