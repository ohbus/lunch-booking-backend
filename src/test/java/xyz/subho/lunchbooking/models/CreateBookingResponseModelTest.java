package xyz.subho.lunchbooking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateBookingResponseModelTest {
  /** Method under test: {@link CreateBookingResponseModel#CreateBookingResponseModel(Long)} */
  @Test
  void testConstructor() {
    assertEquals(123L, (new CreateBookingResponseModel(123L)).id().longValue());
  }
}
