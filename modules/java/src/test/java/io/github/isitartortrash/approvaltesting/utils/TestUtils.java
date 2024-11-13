package io.github.isitartortrash.approvaltesting.utils;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.kotlin.KotlinModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public final class TestUtils {

  public static JsonMapper jsonMapper =
      JsonMapper.builder()
          .disable(WRITE_DATES_AS_TIMESTAMPS)
          .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
          .disable(FAIL_ON_UNKNOWN_PROPERTIES)
          .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
          .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
          .enable(FAIL_ON_MISSING_CREATOR_PROPERTIES, FAIL_ON_NULL_FOR_PRIMITIVES)
          .addModule(new JavaTimeModule())
          .addModule(new Jdk8Module())
          .addModule((new KotlinModule.Builder()).build())
          .build();
}
