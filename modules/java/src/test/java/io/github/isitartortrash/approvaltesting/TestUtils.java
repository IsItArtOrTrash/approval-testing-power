package io.github.isitartortrash.approvaltesting;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;

public final class TestUtils {

  public static JsonMapper jsonMapper =
      JsonMapper.builder()
          .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
          .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
          .addModule(new JavaTimeModule())
          .addModule(new Jdk8Module())
          .build();
}
