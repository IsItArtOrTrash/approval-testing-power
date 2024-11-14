package io.github.isitartortrash.approvaltesting;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Currency {
  EUR("cent"),
  USD("cent");

  private final String monetaryUnit;

  public String getMonetaryUnit() {
    return monetaryUnit;
  }
}
