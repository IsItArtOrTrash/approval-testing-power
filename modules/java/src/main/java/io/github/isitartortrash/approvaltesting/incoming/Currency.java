package io.github.isitartortrash.approvaltesting.incoming;

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
