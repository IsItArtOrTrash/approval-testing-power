package io.github.isitartortrash.approvaltesting.util;

import io.github.isitartortrash.approvaltesting.FakeOrderService;
import io.github.isitartortrash.approvaltesting.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestBase {

  @Mock
  public Clock clock;

  @InjectMocks
  public FakeOrderService orderService;

  @BeforeEach
  void setupClock() {
    Instant now = Instant.now();
    given(clock.instant()).willReturn(now);
    given(clock.getZone()).willReturn(ZoneId.systemDefault());
    given(clock.millis()).willReturn(now.toEpochMilli());
  }
}
